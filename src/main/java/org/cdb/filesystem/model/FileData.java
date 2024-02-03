package org.cdb.filesystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_file_data")
@Getter
@Setter
public class FileData
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id", nullable = false)
    private File fileMetadata;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String fileBlob;
}
