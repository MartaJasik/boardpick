package pl.coderslab.boardpick.XMLscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import pl.coderslab.boardpick.entity.Game;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.round;

@Component
public class Scrapper {

    public static List<String> findFirstTen(String gameToAdd) {
        List<String> idsFound = new ArrayList<>();
        List<String> firstTenIdsFound = new ArrayList<>();

        try {
            org.jsoup.nodes.Document doc = Jsoup.connect("https://boardgamegeek.com/geeksearch.php?action=search&objecttype=boardgame&q=" + gameToAdd).get();
            Elements newsHeadlines = doc.select(".collection_objectname");
            for (Element headline : newsHeadlines) {
                final String url = headline.select("a").attr("href");
                // System.out.println(url);
                Pattern pattern = Pattern.compile("^\\/boardgame\\/(.*)\\/.*$");
                Matcher matcher = pattern.matcher(url);
                while (matcher.find()) {
                    idsFound.add(matcher.group(1));
                }
            }
            if (idsFound.size() > 10) {
                firstTenIdsFound = idsFound.subList(0, 10);
            } else {
                firstTenIdsFound = idsFound;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstTenIdsFound;
    }


    public static Game getGameShort(String gameIdStats) {
        Game game = new Game();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("https://boardgamegeek.com/xmlapi2/thing?id=" + gameIdStats + "&stats=1").openStream());

            String id = doc.getElementsByTagName("item").item(0).getAttributes().getNamedItem("id").getTextContent();
            String name = doc.getElementsByTagName("name").item(0).getAttributes().getNamedItem("value").getTextContent();
            String cover = doc.getElementsByTagName("image").item(0).getTextContent();
            String published = doc.getElementsByTagName("yearpublished").item(0).getAttributes().getNamedItem("value").getTextContent();

            game.setId(Long.parseLong(id));
            game.setName(name);
            game.setCover(cover);
            game.setPublished(published);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public static Game getGameLong(String gameIdStats) {
        Game game = getGameShort(gameIdStats);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("https://boardgamegeek.com/xmlapi2/thing?id=" + gameIdStats + "&stats=1").openStream());

            String minPlayers = doc.getElementsByTagName("minplayers").item(0).getAttributes().getNamedItem("value").getTextContent();
            String maxPlayers = doc.getElementsByTagName("maxplayers").item(0).getAttributes().getNamedItem("value").getTextContent();
            String minPlaytime = doc.getElementsByTagName("minplaytime").item(0).getAttributes().getNamedItem("value").getTextContent();
            String maxPlaytime = doc.getElementsByTagName("maxplaytime").item(0).getAttributes().getNamedItem("value").getTextContent();
            String rating = doc.getElementsByTagName("average").item(0).getAttributes().getNamedItem("value").getTextContent();
            String ranking = doc.getElementsByTagName("rank").item(0).getAttributes().getNamedItem("value").getTextContent();
            String weight = doc.getElementsByTagName("averageweight").item(0).getAttributes().getNamedItem("value").getTextContent();

            game.setMinPlayers(Integer.parseInt(minPlayers));
            game.setMaxPlayers(Integer.parseInt(maxPlayers));
            game.setMinPlaytime(Integer.parseInt(minPlaytime));
            game.setMaxPlaytime(Integer.parseInt(maxPlaytime));
            game.setRating(Math.round(Double.parseDouble(rating) * 100.0) / 100.0);
            game.setRanking(Integer.parseInt(ranking));
            game.setWeight(Math.round(Double.parseDouble(weight) * 100.0) / 100.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public static List<String> advancedFinder(String players, String weight, String time) {
        List<String> idsFound = new ArrayList<>();
        List<String> firstTenIdsFound = new ArrayList<>();
        String weightMin = "";
        String weightMax = "";
        switch (weight) {
            case "easy":
                weightMin = "1";
                weightMax = "2";
                break;
            case "easymedium":
                weightMin = "1";
                weightMax = "3";
                break;
            case "medium":
                weightMin = "2";
                weightMax = "3";
                break;
            case "mediumhard":
                weightMin = "2";
                weightMax = "5";
                break;
            case "hard":
                weightMin = "3";
                weightMax = "5";
                break;
            case "dontcare":
                weightMin = "1";
                weightMax = "5";
                break;
        }

        String timeMin = "";
        String timeMax = "";
        switch (time) {
            case "quick":
                timeMin = "0";
                timeMax = "30";
                break;
            case "quickstandard":
                timeMin = "0";
                timeMax = "60";
                break;
            case "standard":
                timeMin = "30";
                timeMax = "60";
                break;
            case "long":
                timeMin = "60";
                timeMax = "180";
                break;
            case "dontcare":
                timeMin = "0";
                timeMax = "180";
                break;
        }

        try {
            org.jsoup.nodes.Document doc = Jsoup.connect("https://boardgamegeek.com/geeksearch.php?action=search&advsearch=1&objecttype=boardgame&q=&include%5Bdesignerid%5D=&geekitemname=&geekitemname=&include%5Bpublisherid%5D=&range%5Byearpublished%5D%5Bmin%5D=&range%5Byearpublished%5D%5Bmax%5D=&range%5Bminage%5D%5Bmax%5D=&floatrange%5Bavgrating%5D%5Bmin%5D=&floatrange%5Bavgrating%5D%5Bmax%5D=&range%5Bnumvoters%5D%5Bmin%5D=&floatrange%5Bavgweight%5D%5Bmin%5D=" + weightMin + "&floatrange%5Bavgweight%5D%5Bmax%5D=" + weightMax + "&range%5Bnumweights%5D%5Bmin%5D=&colfiltertype=&searchuser=&nosubtypes%5B%5D=boardgameexpansion&range%5Bminplayers%5D%5Bmax%5D=" + players + "&range%5Bmaxplayers%5D%5Bmin%5D=" + players + "&playerrangetype=normal&range%5Bleastplaytime%5D%5Bmin%5D=" + timeMin + "&range%5Bplaytime%5D%5Bmax%5D=" + timeMax + "&B1=Submit").get();
            Elements newsHeadlines = doc.select(".collection_objectname");
            for (Element headline : newsHeadlines) {
                final String url = headline.select("a").attr("href");
                // System.out.println(url);
                Pattern pattern = Pattern.compile("^\\/boardgame\\/(.*)\\/.*$");
                Matcher matcher = pattern.matcher(url);
                while (matcher.find()) {
                    idsFound.add(matcher.group(1));
                }
            }
            if (idsFound.size() > 10) {
                firstTenIdsFound = idsFound.subList(0, 10);
            } else {
                firstTenIdsFound = idsFound;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstTenIdsFound;
    }

}
