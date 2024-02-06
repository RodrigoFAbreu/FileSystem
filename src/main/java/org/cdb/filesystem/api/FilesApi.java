package org.cdb.filesystem.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.cdb.filesystem.dao.file.*;
import org.cdb.filesystem.dao.file.enums.OrderEnum;
import org.cdb.filesystem.model.enums.FileType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Files")
@RequestMapping("/filesystem/v1/files")
public interface FilesApi
{
    @ApiOperation(value = "Add a new file", notes = "Adds a new file to the system")
    @ApiResponses({
            @ApiResponse(code = 201, message = "File successfully added", response = ApiFileAddResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiErrorResponse.class)
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<ApiFileAddResponse> addFile(@Valid @RequestBody ApiFileAddRequest apiFileAddRequest);

    @ApiOperation(value = "List files", notes = "Lists files with optional filters")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = ApiFileListResponse.class)
    })
    @GetMapping(produces = "application/json")
    ResponseEntity<ApiFileListResponse> listFiles(
            @RequestParam(value = "owner", required = false) String owner,
            @RequestParam(value = "fileType", required = false) FileType fileType,
            @RequestParam(value = "filename", required = false) String filename,
            @RequestParam(value = "orderByDate", required = false) OrderEnum orderByDate);

    @ApiOperation(value = "Get file metadata", notes = "Retrieves metadata for a specific file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = ApiFileGetResponse.class),
            @ApiResponse(code = 404, message = "File not found", response = ApiErrorResponse.class)
    })
    @GetMapping(value = "/{fileId}", produces = "application/json")
    ResponseEntity<ApiFileGetResponse> getFile(@PathVariable("fileId") Long fileId);

    @ApiOperation(value = "Get file details", notes = "Retrieves details including data for a specific file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = ApiFileGetDetailsResponse.class)
    })
    @GetMapping(value = "/detail/{fileId}", produces = "application/json")
    ResponseEntity<ApiFileGetDetailsResponse> getFileDetails(@PathVariable("fileId") Long fileId);

    @ApiOperation(value = "Update file", notes = "Updates the metadata and data of a specific file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "File updated successfully", response = ApiFileUpdateResponse.class),
            @ApiResponse(code = 404, message = "File not found", response = ApiErrorResponse.class)
    })
    @PutMapping(value = "/{fileId}", consumes = "application/json")
    ResponseEntity<ApiFileUpdateResponse> updateFile(
            @PathVariable("fileId") Long fileId, @Valid @RequestBody ApiFileUpdateRequest apiFileUpdateRequest);

    @ApiOperation(value = "Delete file", notes = "Soft deletes a specific file by its ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "File deleted successfully"),
            @ApiResponse(code = 404, message = "File not found", response = ApiErrorResponse.class)
    })
    @DeleteMapping("/{fileId}")
    ResponseEntity<Void> deleteFile(@PathVariable("fileId") Long fileId);
}