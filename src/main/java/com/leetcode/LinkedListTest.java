package com.leetcode;

import javax.xml.soap.Node;

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

    public static void main(String[] args) {

    }
}
