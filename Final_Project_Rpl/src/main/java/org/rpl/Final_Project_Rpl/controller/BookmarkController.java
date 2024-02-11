package org.rpl.Final_Project_Rpl.controller;

import org.rpl.Final_Project_Rpl.dto.DisplayBookmarkResponse;
import org.rpl.Final_Project_Rpl.dto.WebResponse;
import org.rpl.Final_Project_Rpl.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
public class BookmarkController {

    @Autowired
    BookmarkService bookmarkService;

    @GetMapping(
            path = "/api/recipe/bookmarks",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebResponse<List<DisplayBookmarkResponse>> getBookmarks(@RequestHeader(name = "X-API-TOKEN") String token) {
        List<DisplayBookmarkResponse> displayBookmarks = bookmarkService.getDisplayBookmarks(token);
        return WebResponse.<List<DisplayBookmarkResponse>>builder().data(displayBookmarks).build();
    }
}
