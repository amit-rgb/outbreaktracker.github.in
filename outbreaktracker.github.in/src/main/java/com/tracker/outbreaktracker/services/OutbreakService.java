package com.tracker.outbreaktracker.services;

import com.tracker.outbreaktracker.models.CountryStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service
public class OutbreakService {
    public static  String BASE_URI="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    ArrayList<CountryStats> countryStatsList = new ArrayList<CountryStats>();

    public ArrayList<CountryStats> getCountryStatsList() {
        return countryStatsList;
    }

    public void setCountryStatsList(ArrayList<CountryStats> countryStatsList) {
        this.countryStatsList = countryStatsList;
    }

    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void getData() throws IOException, InterruptedException {
        ArrayList<CountryStats> newList = new ArrayList<CountryStats>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(BASE_URI)).build();
        HttpResponse httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        StringReader in = new StringReader(httpResponse.body().toString());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            CountryStats countryStats = new CountryStats();
            countryStats.setState(record.get("Province/State"));
            countryStats.setCountry(record.get("Country/Region"));
            int latestRepotedCases = Integer.parseInt(record.get(record.size()-1));
            int previousRepotedCases = Integer.parseInt(record.get(record.size()-2));
            countryStats.setLastCount(latestRepotedCases);
           countryStats.setDiffPreviousDay(latestRepotedCases-previousRepotedCases);
            newList.add(countryStats);
            //System.out.println(countryStats);
        }
        this.countryStatsList=newList;

    }
}
