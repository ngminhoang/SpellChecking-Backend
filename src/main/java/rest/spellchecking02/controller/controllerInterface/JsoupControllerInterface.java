package rest.spellchecking02.controller.controllerInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.spellchecking02.authentication.ContentCheck;
import rest.spellchecking02.authentication.Url;

import java.io.IOException;

@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_USER')")
public interface JsoupControllerInterface {
    @PostMapping("/check")
    public ResponseEntity<ContentCheck> checkUrl(@RequestBody Url url);
}
