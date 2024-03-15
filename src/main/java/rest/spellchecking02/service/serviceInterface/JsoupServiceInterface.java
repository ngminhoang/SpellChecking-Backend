package rest.spellchecking02.service.serviceInterface;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import rest.spellchecking02.authentication.ContentCheck;

import java.io.IOException;

public interface JsoupServiceInterface {
    ContentCheck getData(String link) throws IOException;
}
