package com.example.pgd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class PgdApplication {

	private static Document getPage() throws IOException {
		String url = "https://www.gismeteo.md/weather-tiraspol-4981/";
		Document page = Jsoup.parse(new URL(url), 3000);
		return page;
	}
	// Пн, 16 янв Сегодня +236 +846
	// Пн, \\d{2} янв,

    private static Pattern pattern = Pattern.compile("\\D{2}, \\d{2} \\D{3}");
	private static String getDateFromString(String stringDate) throws Exception {
		Matcher matcher = pattern.matcher(stringDate);
		if (matcher.find()){
			return  matcher.group();
		}
		throw new Exception("Can't extract date from string!");
	}

	private static void printForValues(Elements values, int index){
		for (int i = 0; i < 2; i++){


		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PgdApplication.class, args);
		Document page = getPage();
		//css query language
		Element tableWth = page.select("div[class=content-column column1]").first();
		Elements names = tableWth.select("a[class=weathertab weathertab-block tooltip]");
		Elements values = tableWth.select("div[class=tabe-temp tab-charts]");
		int index = 0;

		for (Element name : names) {
			String stringDate = name.select("div[class=tab-content]").text();
			String date = getDateFromString(stringDate);
			System.out.println(date + " Явления  Температура  Скорость ветра ");
			printForValues(values, index);

		}

		}

	private static class ind {
	}
}


