INSERT INTO tb_permissao (id,descricao) VALUES (1,'ROLE_ALL');

INSERT INTO tb_usuario (id,user_name,password,account_non_expired,account_non_locked,credentials_non_expired,enabled)
    VALUES (1,'admin','$2a$10$Z1K6zJMDWxGKbQZvg3bpN.6ukfxaI/tbZ.dmVhCBNQRa4CKLexRF6','true','true','true','true');

INSERT INTO usuario_permissao (usuario_id,permissao_id) VALUES (1,1);

