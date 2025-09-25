/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  johnny
 * Created: Sep 23, 2025
 */

INSERT INTO grupo (id, active, nome) VALUES
(1, true, 'administrador');

INSERT INTO grupo (id, active, nome) VALUES
(2, true, 'gestor');

INSERT INTO usuario_grupo (usuario_id, grupos_id) VALUES
(2, 1);

CREATE OR REPLACE VIEW v_usuario_grupo AS
SELECT u.login AS username, g.nome AS grupo, u.senha AS senha
FROM usuario u, grupo g, usuario_grupo ug
WHERE u.id = ug.usuario_id AND g.id = ug.grupos_id;