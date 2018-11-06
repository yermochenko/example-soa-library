CREATE DATABASE `soa_library_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT, INSERT, UPDATE, DELETE
ON `soa_library_db`.*
TO soa_library_user@localhost
IDENTIFIED BY 'soa_library_password';

GRANT SELECT, INSERT, UPDATE, DELETE
ON `soa_library_db`.*
TO soa_library_user@'%'
IDENTIFIED BY 'soa_library_password';
