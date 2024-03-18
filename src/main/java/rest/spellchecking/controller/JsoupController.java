package rest.spellchecking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.spellchecking.authentication.ContentCheck;
import rest.spellchecking.authentication.Url;

@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_USER')")
public interface JsoupController {
    @PostMapping("/check")
    public ResponseEntity<ContentCheck> checkUrl(@RequestBody Url url);
}
