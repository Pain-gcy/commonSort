package com.demo.treesort;

/**
 * @author guochunyuan
 * @create on  2020-10-21 15:38
 */

public class AVLTree {

    private class Node {
        int value;
        int bf;
        Node lchild;
        Node rchild;
        Node parent;

        public Node(int value, int bf, Node lchild, Node rchild, Node parent) {
            this.value = value;
            this.bf = bf;
            this.lchild = lchild;
            this.rchild = rchild;
            this.parent = parent;
        }
    }

    Node root = null;
    final int LH = +1;                        /*  左高 */
    final int EH = 0;                            /*  等高 */
    final int RH = -1;                           /*  右高 */
    final int LC = 0;                         //在左子树插入
    final int RC = 1;                            //在右子树插入
    final boolean FALSE = false;
    final boolean TRUE = true;

    /* 对以T为根的二叉排序树作右旋处理 */
    /* 处理之后T的父节点指向T的左节点 */
    //右旋-顺时针旋转(如LL型就得对根结点做该旋转)
    private void R_Rotate(Node T) {
        Node L, P;
        P = T.parent;
        L = T.lchild;                      /*  L指向node的左子树根结点 */
        T.lchild = L.rchild;               /*  L的右子树挂接为node的左子树 */

        if (L.rchild != null)
            L.rchild.parent = T;
        L.rchild = T;
        L.parent = P;
        T.parent = L;

        if (P == null)
            root = L;
        else if (P.rchild == T)
            P.rchild = L;
        else
            P.lchild = L;

    }

    /* 对以T为根的二叉排序树作左旋处理， */
    /* 处理之后T的父节点指向T的右节点 */
    //左旋-逆时针旋转(如RR型就得对根结点做该旋转)
    private void L_Rotate(Node T) {
        Node R, P;
        P = T.parent;
        R = T.rchild;                    /* R指向T的右子树根结点 */
        T.rchild = R.lchild;         /* R的左子树挂接为T的右子树 */

        if (R.lchild != null)
            R.lchild.parent = T;
        R.lchild = T;
        R.parent = P;
        T.parent = R;

        if (P == null) root = R;
        else if (P.rchild == T) P.rchild = R;
        else P.lchild = R;

    }

    /*  对以指针T所指结点为根的二叉树作左平衡旋转处理 */
    /*  本算法结束时，指针T指向新的根结点 */
    public void LeftBalance(Node T) {
        Node L, Lr;
        L = T.lchild;                    /*  L指向T的左子树根结点 */
        switch (L.bf) {
            /* 检查T的左子树的平衡度，并作相应平衡处理 */
            case LH:                        /* 新结点插入在T的左孩子的左子树上，要作单右旋处理 */
                T.bf = L.bf = EH;
                R_Rotate(T);
                break;
            case RH:                        /* 新结点插入在T的左孩子的右子树上，要作双旋处理 */ //
                Lr = L.rchild;                /* Lr指向T的左孩子的右子树根 */
                switch (Lr.bf) {
                    /* 修改T及其左孩子的平衡因子 */
                    case LH:
                        T.bf = RH;
                        L.bf = EH;
                        break;
                    case EH:
                        T.bf = L.bf = EH;
                        break;
                    case RH:
                        T.bf = EH;
                        L.bf = LH;
                        break;
                }
                Lr.bf = EH;
                L_Rotate(T.lchild); /* 对T的左子树作左旋平衡处理 */
                R_Rotate(T);                /* 对T作右旋平衡处理 */
                break;
            case EH:      //特殊情况4,这种情况在添加时不可能出现，只在移除时可能出现，旋转之后整体树高不变
                L.bf = RH;
                T.bf = LH;
                R_Rotate(T);
                break;
        }
    }

    /*  对以指针T所指结点为根的二叉树作右平衡旋转处理， */
    /*  本算法结束时，指针T指向新的根结点 */
    public void RightBalance(Node T) {
        Node R, Rl;
        R = T.rchild;                      /*  R指向T的右子树根结点 */
        switch (R.bf) {
            /*  检查T的右子树的平衡度，并作相应平衡处理 */
            case RH:                        /*  新结点插入在T的右孩子的右子树上，要作单左旋处理 */
                T.bf = R.bf = EH;
                L_Rotate(T);
                break;
            case LH:                        /*  新结点插入在T的右孩子的左子树上，要作双旋处理 */ //最小不平衡树的根结点为负，其右孩子为正
                Rl = R.lchild;                /*  Rl指向T的右孩子的左子树根 */
                switch (Rl.bf) {
                    /*  修改T及其右孩子的平衡因子 */
                    case RH:
                        T.bf = LH;
                        R.bf = EH;
                        break;
                    case EH:
                        T.bf = R.bf = EH;
                        break;
                    case LH:
                        T.bf = EH;
                        R.bf = RH;
                        break;
                }
                Rl.bf = EH;
                R_Rotate(T.rchild); /*  对T的右子树作右旋平衡处理 */
                L_Rotate(T);                /*  对T作左旋平衡处理 */
                break;
            case EH:      //特殊情况4,这种情况在添加时不可能出现，只在移除时可能出现，旋转之后整体树高不变
                R.bf = LH;
                T.bf = RH;
                L_Rotate(T);
                break;

        }
    }


    public void insertAVL(int e) {
        if (root == null) {
            root = new Node(e, EH, null, null, null);
            return;
        }
        TS t = new TS();
        InsertAVL(root, e, t, null);
    }

    /*  若在平衡的二叉排序树T中不存在和e有相同关键字的结点，则插入一个 */
    /*  数据元素为e的新结点，并返回1，否则返回0。若因插入而使二叉排序树 */
    /*  失去平衡，则作平衡旋转处理，布尔变量taller反映T长高与否。 */
    private boolean InsertAVL(Node T, int e, TS tl, Node parent) {
        if (T == null) {
            /*  插入新结点，树“长高”，置taller为TRUE */
            Node nNode = new Node(e, EH, null, null, parent);
            if (parent != null) {
                if (e < parent.value) parent.lchild = nNode;
                else parent.rchild = nNode;
                tl.taller = TRUE;
            }
        } else {
            if (e == T.value) {
                /*  树中已存在和e有相同关键字的结点则不再插入 */
                tl.taller = FALSE;
                return false;
            } else if (e < T.value) {
                /*  应继续在T的左子树中进行搜索 */
                if (!InsertAVL(T.lchild, e, tl, T)) return false;
                if (tl.taller)                             /*   已插入到T的左子树中且左子树“长高” */
                    switch (T.bf)                {
                        case LH:                        /*  原本左子树比右子树高，需要作左平衡处理 */
                            LeftBalance(T);
                            tl.taller = FALSE;
                            break;
                        case EH:                        /*  原本左、右子树等高，现因左子树增高而使树增高 */
                            T.bf = LH;
                            tl.taller = TRUE;
                            break;
                        case RH:                        /*  原本右子树比左子树高，现左、右子树等高 */
                            T.bf = EH;
                            tl.taller = FALSE;
                            break;
                    }
            } else {
                /*  应继续在T的右子树中进行搜索 */
                if (!InsertAVL(T.rchild, e, tl, T)) return false;
                if (tl.taller)                             /*  已插入到T的右子树且右子树“长高” */ {
                    switch (T.bf)                 /*  检查T的平衡度 */ {
                        case LH:                        /*  原本左子树比右子树高，现左、右子树等高 */
                            T.bf = EH;
                            tl.taller = FALSE;
                            break;
                        case EH:                        /*  原本左、右子树等高，现因右子树增高而使树增高  */
                            T.bf = RH;
                            tl.taller = TRUE;
                            break;
                        case RH:                        /*  原本右子树比左子树高，需要作右平衡处理 */
                            RightBalance(T);
                            tl.taller = FALSE;
                            break;
                    }
                }
            }
        }
        return true;
    }

    public boolean deleteAVL(int key) {
        if (root == null) throw new RuntimeException("这是空树！");
        TS ts = new TS();
        return deleteAVL(root, key, ts);
    }

    /*
    若在平衡的二叉排序树t中存在和e有相同关键字的结点，则删除之
    并返回TRUE，否则返回FALSE。若因删除而使二叉排序树
    失去平衡，则作平衡旋转处理，布尔变量shorter反映t变矮与否
    */
    private boolean deleteAVL(Node t, int key, TS ts) {
        if (t == null)                                      //不存在该元素
        {
            return FALSE;                                   //删除失败
        } else if (key == t.value)                           //找到元素结点
        {
            Node q = null;
            if (t.lchild == null)                     //左子树为空
            {
                q = t.parent;
                if (q == null) root = t.rchild;
                else {
                    if (key < q.value) q.lchild = t.rchild;
                    else q.rchild = t.rchild;
                }
                ts.shorter = TRUE;
            } else if (t.rchild == null)                    //右子树为空
            {
                q = t.parent;
                if (q == null) root = t.lchild;
                else {
                    if (key < q.value) q.lchild = t.lchild;
                    else q.rchild = t.lchild;
                }
                ts.shorter = TRUE;
            } else                                            //左右子树都存在,
            {
                q = t.lchild;
                while (q.rchild != null) {
                    q = q.rchild;
                }
                t.value = q.value;
                deleteAVL(t.lchild, q.value, ts);   //在左子树中递归删除前驱结点
            }
        } else if (key < t.value)                         //左子树中继续查找
        {
            if (!deleteAVL(t.lchild, key, ts)) {
                return FALSE;
            }
            if (ts.shorter) {
                switch (t.bf) {
                    case LH:
                        t.bf = EH;
                        ts.shorter = TRUE;
                        break;
                    case EH:
                        t.bf = RH;
                        ts.shorter = FALSE;
                        break;
                    case RH:
                        if (t.rchild.bf == EH)    //注意这里，画图思考一下
                            ts.shorter = FALSE;
                        else ts.shorter = TRUE;
                        RightBalance(t);        //右平衡处理
                        break;
                }
            }
        } else                                //右子树中继续查找
        {
            if (!deleteAVL(t.rchild, key, ts)) {
                return FALSE;
            }
            if (ts.shorter) {
                switch (t.bf) {
                    case LH:
                        if (t.lchild.bf == EH)  //注意这里，画图思考一下
                            ts.shorter = FALSE;
                        else ts.shorter = TRUE;
                        LeftBalance(t);         //左平衡处理
                        break;
                    case EH:
                        t.bf = LH;
                        ts.shorter = FALSE;
                        break;
                    case RH:
                        t.bf = EH;
                        ts.shorter = TRUE;
                        break;
                }
            }
        }
        return TRUE;
    }


    //前序遍历
    private void preOrder(Node r) {
        if (r == null) return;
        System.out.print(r.value + ",");
        preOrder(r.lchild);
        preOrder(r.rchild);
    }

    public void preOrder() {
        preOrder(root);
    }

    //中序遍历
    private void inOrder(Node r) {
        if (r == null) return;
        inOrder(r.lchild);
        System.out.print(r.value + ",");
        inOrder(r.rchild);
    }

    public void inOrder() {
        inOrder(root);
    }

    //中序遍历
    private void postOrder(Node r) {
        if (r == null) return;
        postOrder(r.lchild);
        postOrder(r.rchild);
        System.out.print(r.value + ",");
    }

    public void postOrder() {
        postOrder(root);
    }


    public static void main(String[] args) {
        int[] a = {5, 3, 4, 1, 2, 8, 6, 7};
        AVLTree tree = new AVLTree();
        for (int i = 0; i < a.length; i++) {
            tree.insertAVL(a[i]);
        }
        System.out.println("前序遍历：");
        tree.preOrder();
        System.out.println();
        System.out.println("中序遍历：");//二叉排序树中序遍历是递增序列
        tree.inOrder();
        System.out.println();
        System.out.println("后序遍历：");//二叉排序树中序遍历是递增序列
        tree.postOrder();
        System.out.println();
        tree.deleteAVL(5);
        System.out.println("前序遍历：");
        tree.preOrder();
        System.out.println();
        System.out.println("中序遍历：");//二叉排序树中序遍历是递增序列
        tree.inOrder();
        System.out.println();
        System.out.println("后序遍历：");//二叉排序树中序遍历是递增序列
        tree.postOrder();

    }
}

class TS {
    public boolean taller = false;
    public boolean shorter = false;
}