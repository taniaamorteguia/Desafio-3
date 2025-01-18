CREATE TABLE topicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('activo', 'inactivo') DEFAULT 'activo',
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100),
    respuestas INT DEFAULT 0
);