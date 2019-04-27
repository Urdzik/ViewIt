package com.company.archapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;


public class WikipediaClass {
    @SuppressWarnings("FieldCanBeLocal")
    private String information, url, word, language, start;

    // В функцию нужно передавать название достопримечательности
    @SuppressWarnings("UnusedReturnValue")
    public String findWikipediaText(String word) {
        this.word = word;
        language = Locale.getDefault().getDisplayLanguage();

        //Определяем язык
        switch (language) {
//            case "русский": {
//                language = "ru";
//                start = " — ";
//                break;
//            }
//            case "українська": {
//                language = "uk";
//                start = " — ";
//                break;
//            }
            default: {
                language = "en";
                start = " is ";
                break;
            }
        }

        url = "https://" + language + ".wikipedia.org/wiki/" + word.replaceAll(" ", "_"); //Находим ссылку на Википедию
        Runnable myTask = new MyTask();
        Thread thread = new Thread(myTask);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return information;
    }

    class MyTask implements Runnable {
        @Override
        public void run() {
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
                while (!information.contains(start)) {
                    information = paragraphs.get(ind1).text();
                    ind1++;
                }
                //Если текста в первом абзаце мало, берем ещё и второй
                if (information.length() < 600) {
                    information += "\n" + paragraphs.get(ind1).text();
                }

                //Забираем ненужную информацию
                if (information.contains(")" + start)) ind1 = information.indexOf(")" + start) + 1;
                else ind1 = information.indexOf(start);
                information = information.substring(ind1);
                information = word + information;

                while (information.contains("[")) {
                    ind1 = information.indexOf("[");
                    int ind2 = information.indexOf("]");
                    information = information.substring(0, ind1) + information.substring(ind2 + 1);
                }
            } else
                information = "Article in development";
        }
    }
}
