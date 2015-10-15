package test;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

 

public class testjtree {

    public static void main(String[] args) {

 

        // ����û�и��ڵ���ӽڵ㡢���������ӽڵ�����ڵ㣬��ʹ��ָ�����û�����������г�ʼ����

        // public DefaultMutableTreeNode(Object userObject)

        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("�����");

        node1.add(new DefaultMutableTreeNode(new User("С��")));

        node1.add(new DefaultMutableTreeNode(new User("С��")));

        node1.add(new DefaultMutableTreeNode(new User("С��")));

 

        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("���۲�");

        node2.add(new DefaultMutableTreeNode(new User("СҶ")));

        node2.add(new DefaultMutableTreeNode(new User("С��")));

        node2.add(new DefaultMutableTreeNode(new User("С��")));

 

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("ְԱ����");

 

        top.add(new DefaultMutableTreeNode(new User("�ܾ���")));

        top.add(node1);

        top.add(node2);

        final JTree tree = new JTree(top);

        JFrame f = new JFrame("JTreeDemo");

        f.add(tree);

        f.setSize(300, 300);

        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ���ѡ���¼�

        tree.addTreeSelectionListener(new TreeSelectionListener() {

 

            @Override

            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree

                        .getLastSelectedPathComponent();

 

                if (node == null)

                    return;

 

                Object object = node.getUserObject();
                //String name = node.getUserObject();
                //System.out.println(name);

                if (node.isLeaf()) {

                    User1 user = (User1) object;
                    String name = user.toString();

                    System.out.println("��ѡ���ˣ�" + name);

                }

 

            }

        });

    }

}

class User1 {
    private String name;
    public User1(String n) {
        name = n;
    }
    // �ص���toString���ڵ����ʾ�ı�����toString

    public String toString() {
        return name;
    }
}