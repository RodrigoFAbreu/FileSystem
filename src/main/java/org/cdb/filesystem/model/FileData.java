package org.cdb.filesystem.model;


import jakarta.persistence.*;

@Entity
@Table(name = "t_file_data")
public class FileData
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "fileId", referencedColumnName = "id")
    private File fileMetadata;

    @Lob
    @Column(nullable = false)
    private byte[] fileBlob;
}
