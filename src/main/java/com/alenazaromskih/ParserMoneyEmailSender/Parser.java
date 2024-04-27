package com.alenazaromskih.ParserMoneyEmailSender;
import org.apache.commons.io.output.TeeOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;

public class Parser {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get();//получает html-код страницы
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new TeeOutputStream(System.out, buffer);
        System.setOut(new PrintStream(teeStream));

        Element table = doc.select("table").first(); //находим первую таблицу в документе
        //если надо вторую, третью или т.д. используем .get(номер)

        Elements rows = table.select("tr");// разбиваем нашу таблицу на строки по тегу

        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i); //по номеру индекса получает строку
            Elements cols = row.select("td");// разбиваем полученную строку по тегу  на столбы
            System.out.println("Цифр. код: " + cols.get(0).text() + " ");// первый столбец
            System.out.println("Букв. код: " + cols.get(1).text()+ " ");
            System.out.println("Единиц: " + cols.get(2).text()+ " ");
            System.out.println("Валюта: " + cols.get(3).text()+ " ");
            System.out.println("Курс: " + cols.get(4).text()+ " ");
            System.out.println("____________________________________________________________________________________");
        }
        try(OutputStream fileStream = new FileOutputStream("src/main/java/com/alenazaromskih/ParserMoneyEmailSender/ParserMoney.txt")){
            buffer.writeTo(fileStream);
        }



    }
}
