/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  johnny
 * Created: Sep 23, 2025
 */

INSERT INTO pessoa (id, active, nome, dtype, version) VALUES
(1, true, 'admin', 'Usuario', 1);

INSERT INTO usuario (id, login, senha) VALUES
(1, 'admin', '5eaa29036ed8ce37830a38dbc936a670');

INSERT INTO grupo (id, active, nome, version) VALUES
(1, true, 'administrador', 1);

INSERT INTO grupo (id, active, nome, version) VALUES
(2, true, 'gestor', 1);

INSERT INTO grupo (id, active, nome, version) VALUES
(3, true, 'cidadao', 1);

INSERT INTO usuario_grupo (usuario_id, grupos_id) VALUES
(1, 1);

CREATE OR REPLACE VIEW v_usuario_grupo AS
SELECT u.login AS username, g.nome AS grupo, u.senha AS senha
FROM usuario u, grupo g, usuario_grupo ug
WHERE u.id = ug.usuario_id AND g.id = ug.grupos_id;