package org.cdb.filesystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cdb.filesystem.dao.file.ApiFileAddRequest;
import org.cdb.filesystem.model.enums.FileType;

import java.util.Date;

@Entity
@Table(name = "t_file")
@Getter
@Setter
@NoArgsConstructor
public class File
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileType fileType;

    @Column(nullable = false)
    private Integer fileSize;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

    public File(ApiFileAddRequest aFileAddRequest)
    {
        this.fileName = aFileAddRequest.getFileName();
        this.fileType = FileType.fromString(aFileAddRequest.getFileType());
    }

    @PrePersist
    protected void onCreate()
    {
        createDate = updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        updateDate = new Date();
    }
}
