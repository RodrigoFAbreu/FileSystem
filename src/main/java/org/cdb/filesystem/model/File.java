package org.cdb.filesystem.model;

import jakarta.persistence.*;
import org.cdb.filesystem.model.enums.FileType;

import java.util.Date;

@Entity
@Table(name = "t_file")
public class File
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileType fileType;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

    @PrePersist
    protected void onCreate()
    {
        createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        updateDate = new Date();
    }
}
