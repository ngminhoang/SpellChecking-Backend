package rest.spellchecking02.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking02.authentication.ContentCheck;
import rest.spellchecking02.authentication.Url;
import rest.spellchecking02.controller.controllerInterface.JsoupControllerInterface;
import rest.spellchecking02.service.service.JsoupService;
import rest.spellchecking02.service.serviceInterface.JsoupServiceInterface;

import java.io.IOException;

@RestController
public class JsoupController implements JsoupControllerInterface {
    @Autowired
    private JsoupServiceInterface service;

    public ResponseEntity<ContentCheck> checkUrl(@RequestBody Url url) {
        try {
            ContentCheck result = service.getData(url.url);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
