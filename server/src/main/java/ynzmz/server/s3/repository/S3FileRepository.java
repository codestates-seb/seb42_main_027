package ynzmz.server.s3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.s3.entity.S3File;

@Repository
public interface S3FileRepository extends JpaRepository<S3File,Long> {
}
