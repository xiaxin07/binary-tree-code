package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LinkedListTest {
    /**
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list/*
     *
     * @param head
     * @return
     */
    // 递归
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 非递归写法
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newNode;
            newNode = head;
            head = tmp;
        }
        return newNode;

    }

    /**
     * https://leetcode-cn.com/problems/linked-list-cycle/
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 203：https://leetcode-cn.com/problems/remove-linked-list-elements/
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 虚拟头结点
        head = new ListNode(-1, head);
        // 当前节点
        ListNode node = head;

        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        // 已经为null，提前退出
        if (head == null) {
            return null;
        }
        // 已确定当前head.val != val
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 83：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        head = new ListNode(-101, head);
        ListNode node = head;
        while (node.next.next != null) {
            if (node.next.val == node.next.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }

        }
        return head.next;
    }

    /**
     * 876：https://leetcode-cn.com/problems/middle-of-the-linked-list/
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (head.next == null) {
            return head;
        }
        Map<Integer, ListNode> nodeMap = new HashMap<>();

        ListNode node = head;
        int i = 0;
        while (node != null) {
            nodeMap.put(i++, node);
            node = node.next;
        }
        return nodeMap.get((int)(Math.ceil(i >> 1)));
    }

    public ListNode middleNode2(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
