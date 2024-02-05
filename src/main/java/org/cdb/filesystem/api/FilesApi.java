package org.cdb.filesystem.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.cdb.filesystem.dao.file.*;
import org.cdb.filesystem.dao.file.enums.OrderEnum;
import org.cdb.filesystem.model.enums.FileType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@Tag(name = "Files")
public interface FilesApi
{
    @RequestMapping(value = "/files", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiFileAddResponse> addFile(
            @RequestBody ApiFileAddRequest apiFileAddRequest);

    @RequestMapping(value = "/files", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ApiFileListResponse> listFiles(
            @RequestParam(value = "owner", required = false) String owner,
            @RequestParam(value = "fileType", required = false) FileType fileType,
            @RequestParam(value = "filename", required = false) String filename,
            @RequestParam(value = "orderByDate", required = false) OrderEnum orderByDate);

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ApiFileGetResponse> getFile(
            @PathVariable("fileId") Long fileId);

    @RequestMapping(value = "/files/detail/{fileId}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ApiFileGetDetailsResponse> getFileDetails(
            @PathVariable("fileId") Long fileId);

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.PUT, consumes = "application/json")
    ResponseEntity<ApiFileUpdateResponse> updateFile(
            @PathVariable("fileId") Integer fileId,
            @RequestBody ApiFileUpdateRequest apiFileUpdateRequest);

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteFile(
            @PathVariable("fileId") Long fileId);
}