package com.factorialsigma.betwithbeth.image;

import com.factorialsigma.betwithbeth.model.Sport;
import com.factorialsigma.betwithbeth.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * @author
 * @version 1.0
 */
@Service
@Slf4j
public class ImageService {

    private static final String URL = "http://api.ababeen.com/api/images.php?q=";
    private static final String GURL = "https://www.google.es/search?q=";
    private static final String YURL = "https://es.images.search.yahoo.com/search/images?p=";
    private static final String BURL = "http://www.bing.com/images/search?q=";

    public String getImageUrl(Team team) {
        String name = team.getName();
        name = clean(name + (team.getSport().equals(Sport.FUTBOL) ? " soccer logo" : " atp"));
        log.info("Getting image for " + name);
        try {
            return googleImageSearch(name);
        } catch (Exception ignored) {
            try {
                return yahooImageSearch(name);
            } catch (Exception ignored1) {
                try {
                    return bingImageSearch(name);
                } catch (Exception ignored2) {
                    try {
                        return ababeenImageSearch(name);
                    } catch (Exception ignored3) {
                    }
                }
            }
        }
        return null;
    }


    private String googleImageSearch(String name) throws Exception {
        log.info(GURL + name + "&tbm=isch");
        Document doc = getHtmlDocument(GURL + name + "&tbm=isch");

        Elements entradas = doc.select("img[alt*=Resultado de]");
        String src = entradas.get(0).attr("src");
        if (src == null || src.length() < 7) {
            throw new Exception();
        }
        return src;
    }

    private String yahooImageSearch(String name) throws Exception {
        log.info(YURL + name);
        Document doc = getHtmlDocument(YURL + name);

        Elements entradas = doc.select("noscript");
        String src = entradas.get(1).child(0).attr("src");
        if (src == null || src.length() < 7) {
            throw new Exception();
        }
        return src;
    }

    private String bingImageSearch(String name) throws Exception {
        log.info(BURL + name);
        Document doc = getHtmlDocument(BURL + name);

        Elements entradas = doc.select("img[alt*=Resultado de]");
        String src = entradas.get(0).attr("src");
        if (src == null || src.length() < 7) {
            throw new Exception();
        }
        return src;
    }

    private String ababeenImageSearch(String name) throws Exception {
        String finalUrl = URL + name + "&count=2";
        log.info(finalUrl);
        URL url = new URL(finalUrl);
        InputStream inputStream = url.openStream();

        ObjectMapper mapper = new ObjectMapper();
        List<ImageResponse> images = mapper.readValue(inputStream, new TypeReference<List<ImageResponse>>() {
        });

        String src = null;
        for (ImageResponse image : images) {
            src = image.getTbUrl();
            break;
        }
        if (src == null || src.length() < 7) {
            throw new Exception();
        }
        return src;
    }

    private String clean(String name) {
        String ret = name
                .replace(".", "+")
                .replace("/", "+")
                .replace(" ", "+")
                .replace("+++", "+")
                .replace("++", "+");
        StringBuilder sb = new StringBuilder();
        for (String s : ret.split("\\+")) {
            if (s.length() > 1) {
                sb.append(s).append("+");
            }
        }
        return sb.toString();
    }

    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
            "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10; rv:33.0) Gecko/20100101 Firefox/33.0",
            "Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20130401 Firefox/31.0",
            "Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0",

            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
    };

    /**
     * Con este método devuelvo un objeto de la clase Document con el contenido del
     * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
     *
     * @param url la url
     * @return Documento con el HTML
     */
    protected static Document getHtmlDocument(String url) throws Exception {
        int bodySize = 10 * 1024 * 1024; //10MB
        return Jsoup.connect(url).userAgent(randomFrom(USER_AGENTS)).timeout(100000).maxBodySize(bodySize).get();
    }

    private static <T> T randomFrom(T... items) {
        return items[new Random().nextInt(items.length)];
    }
}
