package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Dao;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final Dao dao = new Dao();

    private JPanel contentPane;
    private JTextField txtNome_1, txtCPF, txtTelefone, txtEmail, txtEndereco, txtBuscar_1;
    private JTextField txtNome_2, txtPreco, txtCategoria, txtBusca_2;
    private JTextArea txtDescricao;
    private JTextField txtCProduto, txtQAtual, txtNProduto, txtEMinimo, txtBuscar_3;
    private JTextField txtCliente, txtProduto, txtQuantidade, txtPrecoU;
    private JTextField txtValorT, txtBuscar_4, txtDataV, txtFormaP;

    private int idCliente, idProduto, idEstoque, idVenda;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    public MainFrame() {
        setTitle("Sistema de vendas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        initialize();
    }

    private void initialize() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JTabbedPane abas = new JTabbedPane();
        abas.setBounds(5, 5, 774, 451);
        contentPane.add(abas);

        // CLIENTES
        JPanel clientes = new JPanel();
        clientes.setLayout(null);
        abas.addTab("Clientes", clientes);

        JButton btnLimparC = new JButton("Limpar");
        btnLimparC.setBounds(10, 10, 84, 35);
        clientes.add(btnLimparC);

        JLabel lblNomeC = new JLabel("Nome:");
        lblNomeC.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNomeC.setBounds(10, 49, 351, 20);
        clientes.add(lblNomeC);
        txtNome_1 = new JTextField();
        txtNome_1.setBounds(10, 81, 351, 22);
        clientes.add(txtNome_1);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCpf.setBounds(10, 115, 351, 20);
        clientes.add(lblCpf);
        txtCPF = new JTextField();
        txtCPF.setBounds(10, 147, 351, 22);
        clientes.add(txtCPF);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblTelefone.setBounds(10, 181, 351, 20);
        clientes.add(lblTelefone);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(10, 213, 351, 22);
        clientes.add(txtTelefone);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEmail.setBounds(10, 247, 351, 20);
        clientes.add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(10, 279, 351, 22);
        clientes.add(txtEmail);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEndereco.setBounds(10, 313, 351, 20);
        clientes.add(lblEndereco);
        txtEndereco = new JTextField();
        txtEndereco.setBounds(10, 345, 351, 22);
        clientes.add(txtEndereco);

        JButton btnAdicionarC = new JButton("Adicionar");
        btnAdicionarC.setBounds(10, 376, 100, 35);
        clientes.add(btnAdicionarC);
        JButton btnAlterarC = new JButton("Alterar");
        btnAlterarC.setBounds(134, 376, 100, 35);
        clientes.add(btnAlterarC);
        JButton btnExcluirC = new JButton("Deletar");
        btnExcluirC.setBounds(261, 376, 100, 35);
        clientes.add(btnExcluirC);

        JSeparator sepC = new JSeparator(SwingConstants.VERTICAL);
        sepC.setBounds(384, 10, 2, 406);
        clientes.add(sepC);

        JLabel lblBuscaC = new JLabel("Buscar clientes por ID:");
        lblBuscaC.setBounds(396, 10, 365, 20);
        clientes.add(lblBuscaC);
        txtBuscar_1 = new JTextField();
        txtBuscar_1.setBounds(396, 40, 271, 22);
        clientes.add(txtBuscar_1);
        JButton btnBuscarC = new JButton("Buscar");
        btnBuscarC.setBounds(677, 35, 84, 35);
        clientes.add(btnBuscarC);

        btnLimparC.addActionListener(e -> limparCliente());
        btnAdicionarC.addActionListener(e -> salvarCliente());
        btnAlterarC.addActionListener(e -> alterarCliente());
        btnExcluirC.addActionListener(e -> excluirCliente());
        btnBuscarC.addActionListener(e -> pesquisarCliente());

        // PRODUTOS
        JPanel produtos = new JPanel();
        produtos.setLayout(null);
        abas.addTab("Produtos", produtos);

        JButton btnLimparP = new JButton("Limpar");
        btnLimparP.setBounds(10, 10, 84, 35);
        produtos.add(btnLimparP);

        JLabel lblNomeP = new JLabel("Nome:");
        lblNomeP.setBounds(10, 46, 351, 20);
        produtos.add(lblNomeP);
        txtNome_2 = new JTextField();
        txtNome_2.setBounds(10, 78, 351, 22);
        produtos.add(txtNome_2);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(10, 112, 351, 20);
        produtos.add(lblPreco);
        txtPreco = new JTextField();
        txtPreco.setBounds(10, 144, 351, 22);
        produtos.add(txtPreco);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(10, 178, 351, 20);
        produtos.add(lblCategoria);
        txtCategoria = new JTextField();
        txtCategoria.setBounds(10, 210, 351, 22);
        produtos.add(txtCategoria);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(10, 244, 351, 20);
        produtos.add(lblDescricao);
        txtDescricao = new JTextArea();
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricao);
        scrollDescricao.setBounds(10, 276, 351, 90);
        produtos.add(scrollDescricao);

        JButton btnAdicionarP = new JButton("Adicionar");
        btnAdicionarP.setBounds(10, 376, 100, 35);
        produtos.add(btnAdicionarP);
        JButton btnAlterarP = new JButton("Alterar");
        btnAlterarP.setBounds(134, 376, 100, 35);
        produtos.add(btnAlterarP);
        JButton btnExcluirP = new JButton("Deletar");
        btnExcluirP.setBounds(261, 376, 100, 35);
        produtos.add(btnExcluirP);

        JSeparator sepP = new JSeparator(SwingConstants.VERTICAL);
        sepP.setBounds(384, 10, 2, 406);
        produtos.add(sepP);

        JLabel lblBuscaP = new JLabel("Buscar produtos por ID:");
        lblBuscaP.setBounds(396, 10, 365, 20);
        produtos.add(lblBuscaP);
        txtBusca_2 = new JTextField();
        txtBusca_2.setBounds(396, 40, 271, 22);
        produtos.add(txtBusca_2);
        JButton btnBuscarP = new JButton("Buscar");
        btnBuscarP.setBounds(677, 35, 84, 35);
        produtos.add(btnBuscarP);

        btnLimparP.addActionListener(e -> limparProduto());
        btnAdicionarP.addActionListener(e -> salvarProduto());
        btnAlterarP.addActionListener(e -> alterarProduto());
        btnExcluirP.addActionListener(e -> excluirProduto());
        btnBuscarP.addActionListener(e -> pesquisarProduto());

        // ESTOQUE
        JPanel estoque = new JPanel();
        estoque.setLayout(null);
        abas.addTab("Estoque", estoque);

        JButton btnLimparE = new JButton("Limpar");
        btnLimparE.setBounds(10, 11, 84, 35);
        estoque.add(btnLimparE);

        JLabel lblCodigo = new JLabel("Código do Produto (ID):");
        lblCodigo.setBounds(10, 48, 351, 20);
        estoque.add(lblCodigo);
        txtCProduto = new JTextField();
        txtCProduto.setBounds(10, 80, 351, 22);
        estoque.add(txtCProduto);

        JLabel lblQtdAtual = new JLabel("Quantidade Atual:");
        lblQtdAtual.setBounds(10, 114, 351, 20);
        estoque.add(lblQtdAtual);
        txtQAtual = new JTextField();
        txtQAtual.setBounds(10, 146, 351, 22);
        estoque.add(txtQAtual);

        JLabel lblNomeProduto = new JLabel("Nome do Produto:");
        lblNomeProduto.setBounds(10, 180, 351, 20);
        estoque.add(lblNomeProduto);
        txtNProduto = new JTextField();
        txtNProduto.setEditable(false);
        txtNProduto.setBounds(10, 212, 351, 22);
        estoque.add(txtNProduto);

        JLabel lblMinimo = new JLabel("Estoque Mínimo:");
        lblMinimo.setBounds(10, 246, 351, 20);
        estoque.add(lblMinimo);
        txtEMinimo = new JTextField();
        txtEMinimo.setBounds(10, 278, 351, 22);
        estoque.add(txtEMinimo);

        JButton btnAdicionarE = new JButton("Adicionar");
        btnAdicionarE.setBounds(10, 339, 100, 35);
        estoque.add(btnAdicionarE);
        JButton btnAlterarE = new JButton("Alterar");
        btnAlterarE.setBounds(134, 339, 100, 35);
        estoque.add(btnAlterarE);
        JButton btnExcluirE = new JButton("Deletar");
        btnExcluirE.setBounds(261, 339, 100, 35);
        estoque.add(btnExcluirE);

        JSeparator sepE = new JSeparator(SwingConstants.VERTICAL);
        sepE.setBounds(384, 10, 2, 406);
        estoque.add(sepE);

        JLabel lblBuscaE = new JLabel("Buscar estoque por ID:");
        lblBuscaE.setBounds(394, 50, 365, 20);
        estoque.add(lblBuscaE);
        txtBuscar_3 = new JTextField();
        txtBuscar_3.setBounds(394, 80, 271, 22);
        estoque.add(txtBuscar_3);
        JButton btnBuscarE = new JButton("Buscar");
        btnBuscarE.setBounds(675, 75, 84, 35);
        estoque.add(btnBuscarE);

        btnLimparE.addActionListener(e -> limparEstoque());
        btnAdicionarE.addActionListener(e -> salvarEstoque());
        btnAlterarE.addActionListener(e -> alterarEstoque());
        btnExcluirE.addActionListener(e -> excluirEstoque());
        btnBuscarE.addActionListener(e -> pesquisarEstoque());

        txtCProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                preencherNomeProduto();
            }
        });

        // VENDAS
        JPanel vendas = new JPanel();
        vendas.setLayout(null);
        abas.addTab("Vendas", vendas);

        JButton btnLimparV = new JButton("Limpar");
        btnLimparV.setBounds(10, 13, 84, 35);
        vendas.add(btnLimparV);

        JLabel lblCliente = new JLabel("Cliente (nome):");
        lblCliente.setBounds(10, 50, 351, 20);
        vendas.add(lblCliente);
        txtCliente = new JTextField();
        txtCliente.setBounds(10, 82, 351, 22);
        vendas.add(txtCliente);

        JLabel lblProduto = new JLabel("Produto (nome):");
        lblProduto.setBounds(10, 116, 351, 20);
        vendas.add(lblProduto);
        txtProduto = new JTextField();
        txtProduto.setBounds(10, 148, 351, 22);
        vendas.add(txtProduto);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(10, 182, 351, 20);
        vendas.add(lblQuantidade);
        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(10, 214, 351, 22);
        vendas.add(txtQuantidade);

        JLabel lblPrecoU = new JLabel("Preço Unitário:");
        lblPrecoU.setBounds(10, 248, 351, 20);
        vendas.add(lblPrecoU);
        txtPrecoU = new JTextField();
        txtPrecoU.setBounds(10, 280, 351, 22);
        vendas.add(txtPrecoU);

        JLabel lblValorTotal = new JLabel("Valor Total:");
        lblValorTotal.setBounds(10, 314, 351, 20);
        vendas.add(lblValorTotal);
        txtValorT = new JTextField();
        txtValorT.setEditable(false);
        txtValorT.setBounds(10, 346, 351, 22);
        vendas.add(txtValorT);

        JButton btnAdicionarV = new JButton("Adicionar");
        btnAdicionarV.setBounds(10, 377, 100, 35);
        vendas.add(btnAdicionarV);
        JButton btnAlterarV = new JButton("Alterar");
        btnAlterarV.setBounds(134, 377, 100, 35);
        vendas.add(btnAlterarV);
        JButton btnExcluirV = new JButton("Deletar");
        btnExcluirV.setBounds(261, 377, 100, 35);
        vendas.add(btnExcluirV);

        JLabel lblData = new JLabel("Data da Venda (AAAA-MM-DD):");
        lblData.setBounds(394, 50, 351, 20);
        vendas.add(lblData);
        txtDataV = new JTextField();
        txtDataV.setBounds(394, 82, 351, 22);
        vendas.add(txtDataV);

        JLabel lblForma = new JLabel("Forma de Pagamento:");
        lblForma.setBounds(394, 119, 351, 20);
        vendas.add(lblForma);
        txtFormaP = new JTextField();
        txtFormaP.setBounds(394, 151, 351, 22);
        vendas.add(txtFormaP);

        JLabel lblBuscaV = new JLabel("Buscar vendas por ID:");
        lblBuscaV.setBounds(394, 219, 365, 20);
        vendas.add(lblBuscaV);
        txtBuscar_4 = new JTextField();
        txtBuscar_4.setBounds(394, 249, 271, 22);
        vendas.add(txtBuscar_4);
        JButton btnBuscarV = new JButton("Buscar");
        btnBuscarV.setBounds(675, 244, 84, 35);
        vendas.add(btnBuscarV);

        btnLimparV.addActionListener(e -> limparVenda());
        btnAdicionarV.addActionListener(e -> salvarVenda());
        btnAlterarV.addActionListener(e -> alterarVenda());
        btnExcluirV.addActionListener(e -> excluirVenda());
        btnBuscarV.addActionListener(e -> pesquisarVenda());

        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                calcularTotal();
            }
        });
        txtPrecoU.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                calcularTotal();
            }
        });
    }

    // MÉTODOS GERAIS
    private void mensagem(String texto) {
        JOptionPane.showMessageDialog(this, texto);
    }

    private boolean confirmar(String texto) {
        return JOptionPane.showConfirmDialog(this, texto, "Confirmação",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private boolean obrigatorios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                mensagem("Preencha os campos obrigatórios.");
                return false;
            }
        }
        return true;
    }

    private int inteiro(String valor) {
        return Integer.parseInt(valor.trim());
    }

    private double decimal(String valor) {
        return Double.parseDouble(valor.trim().replace(",", "."));
    }

    private void limpar(JTextField... campos) {
        for (JTextField campo : campos) campo.setText("");
    }

    private void preencher(PreparedStatement pst, Object... valores) throws SQLException {
        for (int i = 0; i < valores.length; i++)
            pst.setObject(i + 1, valores[i]);
    }

    private int executar(String sql, Object... valores) throws Exception {
        try (Connection con = dao.conexao();
             PreparedStatement pst = con.prepareStatement(sql)) {
            preencher(pst, valores);
            return pst.executeUpdate();
        }
    }

    private Object[] consultar(String sql, Object... valores) throws Exception {
        try (Connection con = dao.conexao();
             PreparedStatement pst = con.prepareStatement(sql)) {
            preencher(pst, valores);
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) return null;
                ResultSetMetaData md = rs.getMetaData();
                Object[] r = new Object[md.getColumnCount()];
                for (int i = 0; i < r.length; i++) r[i] = rs.getObject(i + 1);
                return r;
            }
        }
    }

    private int buscarId(String tabela, String coluna, String nome) throws Exception {
        Object[] r = consultar("SELECT " + coluna + " FROM " + tabela + " WHERE nome=?", nome.trim());
        return r == null ? 0 : ((Number) r[0]).intValue();
    }

    private void pesquisar(String sql, JTextField busca, Consumer<Object[]> preencher, String naoEncontrado) {
        try {
            Object[] r = consultar(sql, inteiro(busca.getText()));
            if (r == null) mensagem(naoEncontrado);
            else preencher.accept(r);
        } catch (Exception e) {
            mensagem("Informe um ID válido.");
        }
    }

    private void excluir(String sql, int id, String pergunta, String sucesso, String erro, Runnable limpar) {
        if (id == 0) {
            mensagem("Pesquise um registro antes de excluir.");
            return;
        }
        if (!confirmar(pergunta)) return;
        try {
            executar(sql, id);
            mensagem(sucesso);
            limpar.run();
        } catch (Exception e) {
            mensagem(erro);
        }
    }

    // CLIENTES
    private void salvarCliente() {
        if (!obrigatorios(txtNome_1.getText(), txtCPF.getText())) return;
        try {
            executar("INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)",
                    txtNome_1.getText(), txtCPF.getText(), txtTelefone.getText(),
                    txtEmail.getText(), txtEndereco.getText());
            mensagem("Cliente cadastrado com sucesso!");
            limparCliente();
        } catch (Exception e) {
            mensagem("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private void pesquisarCliente() {
        pesquisar("SELECT id_cliente, nome, cpf, telefone, email, endereco FROM clientes WHERE id_cliente=?",
                txtBuscar_1, r -> {
                    idCliente = ((Number) r[0]).intValue();
                    txtNome_1.setText(String.valueOf(r[1]));
                    txtCPF.setText(String.valueOf(r[2]));
                    txtTelefone.setText(String.valueOf(r[3]));
                    txtEmail.setText(String.valueOf(r[4]));
                    txtEndereco.setText(String.valueOf(r[5]));
                    mensagem("Cliente encontrado!");
                }, "Cliente não encontrado.");
    }

    private void alterarCliente() {
        if (idCliente == 0) {
            mensagem("Pesquise um cliente antes de alterar.");
            return;
        }
        if (!obrigatorios(txtNome_1.getText(), txtCPF.getText())) return;
        try {
            executar("UPDATE clientes SET nome=?, cpf=?, telefone=?, email=?, endereco=? WHERE id_cliente=?",
                    txtNome_1.getText(), txtCPF.getText(), txtTelefone.getText(),
                    txtEmail.getText(), txtEndereco.getText(), idCliente);
            mensagem("Cliente alterado!");
            limparCliente();
        } catch (Exception e) {
            mensagem("Erro ao alterar cliente: " + e.getMessage());
        }
    }

    private void excluirCliente() {
        excluir("DELETE FROM clientes WHERE id_cliente=?", idCliente,
                "Deseja excluir este cliente?", "Cliente excluído!",
                "Não foi possível excluir. O cliente pode possuir vendas.", this::limparCliente);
    }

    private void limparCliente() {
        limpar(txtNome_1, txtCPF, txtTelefone, txtEmail, txtEndereco, txtBuscar_1);
        idCliente = 0;
    }

    // PRODUTOS
    private void salvarProduto() {
        if (!obrigatorios(txtNome_2.getText(), txtPreco.getText())) return;
        try {
            double preco = decimal(txtPreco.getText());
            if (preco < 0) {
                mensagem("O preço não pode ser negativo.");
                return;
            }
            executar("INSERT INTO produtos (nome, preco, categoria, descricao) VALUES (?, ?, ?, ?)",
                    txtNome_2.getText(), preco, txtCategoria.getText(), txtDescricao.getText());
            mensagem("Produto cadastrado!");
            limparProduto();
        } catch (Exception e) {
            mensagem("Erro ao cadastrar produto. Confira o preço.");
        }
    }

    private void pesquisarProduto() {
        pesquisar("SELECT id_produto, nome, preco, categoria, descricao FROM produtos WHERE id_produto=?",
                txtBusca_2, r -> {
                    idProduto = ((Number) r[0]).intValue();
                    txtNome_2.setText(String.valueOf(r[1]));
                    txtPreco.setText(String.valueOf(r[2]));
                    txtCategoria.setText(String.valueOf(r[3]));
                    txtDescricao.setText(String.valueOf(r[4]));
                    mensagem("Produto encontrado!");
                }, "Produto não encontrado.");
    }

    private void alterarProduto() {
        if (idProduto == 0) {
            mensagem("Pesquise um produto antes de alterar.");
            return;
        }
        if (!obrigatorios(txtNome_2.getText(), txtPreco.getText())) return;
        try {
            double preco = decimal(txtPreco.getText());
            if (preco < 0) {
                mensagem("O preço não pode ser negativo.");
                return;
            }
            executar("UPDATE produtos SET nome=?, preco=?, categoria=?, descricao=? WHERE id_produto=?",
                    txtNome_2.getText(), preco, txtCategoria.getText(), txtDescricao.getText(), idProduto);
            mensagem("Produto alterado!");
            limparProduto();
        } catch (Exception e) {
            mensagem("Erro ao alterar produto: " + e.getMessage());
        }
    }

    private void excluirProduto() {
        excluir("DELETE FROM produtos WHERE id_produto=?", idProduto,
                "Deseja excluir este produto?", "Produto excluído!",
                "Não foi possível excluir. O produto pode estar no estoque ou em uma venda.", this::limparProduto);
    }

    private void limparProduto() {
        limpar(txtNome_2, txtPreco, txtCategoria, txtBusca_2);
        txtDescricao.setText("");
        idProduto = 0;
    }

    // ESTOQUE
    private void preencherNomeProduto() {
        try {
            if (txtCProduto.getText().trim().isEmpty()) {
                txtNProduto.setText("");
                return;
            }
            Object[] r = consultar("SELECT nome FROM produtos WHERE id_produto=?", inteiro(txtCProduto.getText()));
            txtNProduto.setText(r == null ? "" : String.valueOf(r[0]));
        } catch (Exception e) {
            txtNProduto.setText("");
        }
    }

    private void salvarEstoque() {
        if (!obrigatorios(txtCProduto.getText(), txtQAtual.getText(), txtEMinimo.getText())) return;
        try {
            int codigo = inteiro(txtCProduto.getText());
            int qtd = inteiro(txtQAtual.getText());
            int minimo = inteiro(txtEMinimo.getText());
            if (qtd < 0 || minimo < 0) {
                mensagem("As quantidades não podem ser negativas.");
                return;
            }
            executar("INSERT INTO estoque (id_produto, quantidade_atual, estoque_minimo) VALUES (?, ?, ?)",
                    codigo, qtd, minimo);
            mensagem("Estoque cadastrado!");
            limparEstoque();
        } catch (Exception e) {
            mensagem("Erro ao cadastrar. Confira o produto e os dados do estoque.");
        }
    }

    private void pesquisarEstoque() {
        pesquisar("SELECT e.id_estoque, e.id_produto, e.quantidade_atual, p.nome, e.estoque_minimo " +
                "FROM estoque e INNER JOIN produtos p ON e.id_produto=p.id_produto WHERE e.id_estoque=?",
                txtBuscar_3, r -> {
                    idEstoque = ((Number) r[0]).intValue();
                    txtCProduto.setText(String.valueOf(r[1]));
                    txtQAtual.setText(String.valueOf(r[2]));
                    txtNProduto.setText(String.valueOf(r[3]));
                    txtEMinimo.setText(String.valueOf(r[4]));
                    mensagem("Estoque encontrado!");
                }, "Estoque não encontrado.");
    }

    private void alterarEstoque() {
        if (idEstoque == 0) {
            mensagem("Pesquise um estoque antes de alterar.");
            return;
        }
        if (!obrigatorios(txtCProduto.getText(), txtQAtual.getText(), txtEMinimo.getText())) return;
        try {
            int codigo = inteiro(txtCProduto.getText());
            int qtd = inteiro(txtQAtual.getText());
            int minimo = inteiro(txtEMinimo.getText());
            if (qtd < 0 || minimo < 0) {
                mensagem("As quantidades não podem ser negativas.");
                return;
            }
            executar("UPDATE estoque SET id_produto=?, quantidade_atual=?, estoque_minimo=? WHERE id_estoque=?",
                    codigo, qtd, minimo, idEstoque);
            mensagem("Estoque alterado!");
            limparEstoque();
        } catch (Exception e) {
            mensagem("Erro ao alterar estoque. Confira os dados.");
        }
    }

    private void excluirEstoque() {
        excluir("DELETE FROM estoque WHERE id_estoque=?", idEstoque,
                "Deseja excluir este estoque?", "Estoque excluído!",
                "Erro ao excluir estoque.", this::limparEstoque);
    }

    private void limparEstoque() {
        limpar(txtCProduto, txtQAtual, txtNProduto, txtEMinimo, txtBuscar_3);
        idEstoque = 0;
    }

    // VENDAS
    private void calcularTotal() {
        try {
            double total = inteiro(txtQuantidade.getText()) * decimal(txtPrecoU.getText());
            txtValorT.setText(String.format(Locale.US, "%.2f", total));
        } catch (Exception e) {
            txtValorT.setText("");
        }
    }

    private boolean verificarEstoque(Connection con, int produto, int quantidade) throws Exception {
        try (PreparedStatement pst = con.prepareStatement(
                "SELECT quantidade_atual FROM estoque WHERE id_produto=? FOR UPDATE")) {
            pst.setInt(1, produto);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() && rs.getInt("quantidade_atual") >= quantidade;
            }
        }
    }

    private void atualizarEstoque(Connection con, int produto, int quantidade) throws Exception {
        try (PreparedStatement pst = con.prepareStatement(
                "UPDATE estoque SET quantidade_atual=quantidade_atual+? WHERE id_produto=?")) {
            pst.setInt(1, quantidade);
            pst.setInt(2, produto);
            pst.executeUpdate();
        }
    }

    private void executarNaConexao(Connection con, String sql, Object... valores) throws Exception {
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            preencher(pst, valores);
            pst.executeUpdate();
        }
    }

    private Object[] dadosVenda() throws Exception {
        int cliente = buscarId("clientes", "id_cliente", txtCliente.getText());
        int produto = buscarId("produtos", "id_produto", txtProduto.getText());
        int qtd = inteiro(txtQuantidade.getText());
        double preco = decimal(txtPrecoU.getText());
        LocalDate data = LocalDate.parse(txtDataV.getText().trim());

        if (cliente == 0 || produto == 0)
            throw new Exception("Cliente ou produto não encontrado.");
        if (qtd <= 0 || preco < 0)
            throw new Exception("Informe uma quantidade maior que zero e um preço válido.");

        return new Object[] { cliente, produto, qtd, preco, data };
    }

    private boolean camposVendaPreenchidos() {
        return obrigatorios(txtCliente.getText(), txtProduto.getText(), txtQuantidade.getText(),
                txtPrecoU.getText(), txtDataV.getText(), txtFormaP.getText());
    }

    private void salvarVenda() {
        if (!camposVendaPreenchidos()) return;
        try {
            Object[] d = dadosVenda();
            int cliente = (int) d[0], produto = (int) d[1], qtd = (int) d[2];
            double preco = (double) d[3];
            LocalDate data = (LocalDate) d[4];

            try (Connection con = dao.conexao()) {
                con.setAutoCommit(false);
                try {
                    if (!verificarEstoque(con, produto, qtd))
                        throw new Exception("Estoque insuficiente ou não cadastrado.");

                    executarNaConexao(con,
                            "INSERT INTO vendas (id_cliente, id_produto, quantidade, preco_unitario, valor_total, data_venda, forma_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)",
                            cliente, produto, qtd, preco, qtd * preco, Date.valueOf(data), txtFormaP.getText());
                    atualizarEstoque(con, produto, -qtd);
                    con.commit();
                } catch (Exception e) {
                    con.rollback();
                    throw e;
                }
            }
            mensagem("Venda cadastrada!");
            limparVenda();
        } catch (DateTimeParseException e) {
            mensagem("Use a data no formato AAAA-MM-DD.");
        } catch (Exception e) {
            mensagem("Erro ao cadastrar venda: " + e.getMessage());
        }
    }

    private void pesquisarVenda() {
        pesquisar("SELECT v.id_venda, c.nome, p.nome, v.quantidade, v.preco_unitario, " +
                "v.valor_total, v.data_venda, v.forma_pagamento " +
                "FROM vendas v INNER JOIN clientes c ON v.id_cliente=c.id_cliente " +
                "INNER JOIN produtos p ON v.id_produto=p.id_produto WHERE v.id_venda=?",
                txtBuscar_4, r -> {
                    idVenda = ((Number) r[0]).intValue();
                    txtCliente.setText(String.valueOf(r[1]));
                    txtProduto.setText(String.valueOf(r[2]));
                    txtQuantidade.setText(String.valueOf(r[3]));
                    txtPrecoU.setText(String.valueOf(r[4]));
                    txtValorT.setText(String.valueOf(r[5]));
                    txtDataV.setText(String.valueOf(r[6]));
                    txtFormaP.setText(String.valueOf(r[7]));
                    mensagem("Venda encontrada!");
                }, "Venda não encontrada.");
    }

    private void alterarVenda() {
        if (idVenda == 0) {
            mensagem("Pesquise uma venda antes de alterar.");
            return;
        }
        if (!camposVendaPreenchidos()) return;
        try {
            Object[] d = dadosVenda();
            int cliente = (int) d[0], produto = (int) d[1], qtd = (int) d[2];
            double preco = (double) d[3];
            LocalDate data = (LocalDate) d[4];

            try (Connection con = dao.conexao()) {
                con.setAutoCommit(false);
                try {
                    int produtoAntigo, qtdAntiga;
                    try (PreparedStatement pst = con.prepareStatement(
                            "SELECT id_produto, quantidade FROM vendas WHERE id_venda=? FOR UPDATE")) {
                        pst.setInt(1, idVenda);
                        try (ResultSet rs = pst.executeQuery()) {
                            if (!rs.next()) throw new Exception("Venda não encontrada.");
                            produtoAntigo = rs.getInt("id_produto");
                            qtdAntiga = rs.getInt("quantidade");
                        }
                    }

                    atualizarEstoque(con, produtoAntigo, qtdAntiga);
                    if (!verificarEstoque(con, produto, qtd))
                        throw new Exception("Estoque insuficiente para essa alteração.");

                    executarNaConexao(con,
                            "UPDATE vendas SET id_cliente=?, id_produto=?, quantidade=?, preco_unitario=?, valor_total=?, data_venda=?, forma_pagamento=? WHERE id_venda=?",
                            cliente, produto, qtd, preco, qtd * preco, Date.valueOf(data),
                            txtFormaP.getText(), idVenda);
                    atualizarEstoque(con, produto, -qtd);
                    con.commit();
                } catch (Exception e) {
                    con.rollback();
                    throw e;
                }
            }
            mensagem("Venda alterada!");
            limparVenda();
        } catch (DateTimeParseException e) {
            mensagem("Use a data no formato AAAA-MM-DD.");
        } catch (Exception e) {
            mensagem("Erro ao alterar venda: " + e.getMessage());
        }
    }

    private void excluirVenda() {
        if (idVenda == 0) {
            mensagem("Pesquise uma venda antes de excluir.");
            return;
        }
        if (!confirmar("Deseja excluir esta venda?")) return;

        try (Connection con = dao.conexao()) {
            con.setAutoCommit(false);
            try {
                int produto, qtd;
                try (PreparedStatement pst = con.prepareStatement(
                        "SELECT id_produto, quantidade FROM vendas WHERE id_venda=? FOR UPDATE")) {
                    pst.setInt(1, idVenda);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (!rs.next()) throw new Exception("Venda não encontrada.");
                        produto = rs.getInt("id_produto");
                        qtd = rs.getInt("quantidade");
                    }
                }
                executarNaConexao(con, "DELETE FROM vendas WHERE id_venda=?", idVenda);
                atualizarEstoque(con, produto, qtd);
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
            mensagem("Venda excluída e estoque atualizado!");
            limparVenda();
        } catch (Exception e) {
            mensagem("Erro ao excluir venda: " + e.getMessage());
        }
    }

    private void limparVenda() {
        limpar(txtCliente, txtProduto, txtQuantidade, txtPrecoU,
                txtValorT, txtDataV, txtFormaP, txtBuscar_4);
        idVenda = 0;
    }
}

