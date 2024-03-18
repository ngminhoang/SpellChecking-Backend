package rest.spellchecking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking.authentication.ContentCheck;
import rest.spellchecking.authentication.Url;
import rest.spellchecking.service.JsoupService;

import java.io.IOException;

@RestController
public class JsoupControllerImpl implements JsoupController {
    @Autowired
    private JsoupService jsoupService;

    public ResponseEntity<ContentCheck> checkUrl(@RequestBody Url url) {
        try {
            ContentCheck result = jsoupService.getData(url.url);
            if (result == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
