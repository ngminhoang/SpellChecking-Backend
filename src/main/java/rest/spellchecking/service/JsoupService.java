package rest.spellchecking.service;

import rest.spellchecking.authentication.ContentCheck;

import java.io.IOException;

public interface JsoupService {
    ContentCheck getData(String link) throws IOException;
}
