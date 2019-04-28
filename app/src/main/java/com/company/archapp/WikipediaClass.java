package com.company.archapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;


public class WikipediaClass {
    private String information, url, word;
    private TextView textview;
    private ProgressBar progressBar;
    private SlidingUpPanelLayout slidingUpPanelLayout;

    //В функцию нужно передавать название достопримечательности и textview в каком будет показыватся информация про неё
    public void findWikipediaText(String word, TextView textview, ProgressBar progressBar, SlidingUpPanelLayout slidingUpPanelLayout) {
        this.textview = textview;
        this.progressBar = progressBar;
        this.slidingUpPanelLayout = slidingUpPanelLayout;
        this.word = word;

        url = "https://en.wikipedia.org/wiki/" + word.replaceAll(" ", "_"); //Находим ссылку на Википедию
        MyTask mt = new MyTask();
        mt.execute();
    }

    @SuppressLint("StaticFieldLeak")
    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Document doc = null;
            try {
                //Ищем html документ за url
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                //Если не получилось считать
                e.printStackTrace();
            }

            //Если всё считалось, берем из документа нужный абзац
            if (doc != null) {
                Elements paragraphs = doc.select("p");
                //Ищем нужный абзац
                information = "";
                int ind1 = 0;
                while (information.length() < 150) {
                    information = paragraphs.get(ind1).text();
                    ind1++;
                }
                //Если текста в первом абзаце мало, берем ещё и второй
                if (information.length() < 300) {
                    information += "\n" + paragraphs.get(ind1).text();
                }

                //Забираем ненужную информацию
                if (information.contains(" is ")) {
                    if (information.contains(") is ")) ind1 = information.indexOf(") is ") + 1;
                    else ind1 = information.indexOf(" is ");
                    information = information.substring(ind1);
                    information = word + information;
                }

                while (information.contains("[")) {
                    ind1 = information.indexOf("[");
                    int ind2 = information.indexOf("]");
                    information = information.substring(0, ind1) + information.substring(ind2 + 1);
                }
            } else
                information = "Article in development";
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            textview.setText(information);
            progressBar.setVisibility(View.GONE);
            slidingUpPanelLayout.setVisibility(View.VISIBLE);
        }
    }
}
