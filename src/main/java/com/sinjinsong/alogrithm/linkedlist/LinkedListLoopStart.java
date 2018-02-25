package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class LinkedListLoopStart {
    /**
     * 快慢指针
     * 最优解使用快慢指针实现，快指针一次走两步，慢指针一次走一步，
     * 如果无环，那么快指针会发现下一个为null。
     * 如果有环，那么一定会在环中的某个节点相遇。相遇时，快指针重新指向头节点，
     * 快指针和慢指针以同样速度继续遍历，再次相遇的节点即为进入环的第一个节点。
     *
     * @param head
     * @return
     */
    public static ListNode getLoopStart(ListNode head) {
        ListNode fast = head, slow = head;
        if (head == null || head.next == null) {
            return null;
        }
        // 至少有两个节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        // 相遇，有环
        if (fast == slow) {
            // fast重新指向头节点
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            // 相遇，即为环开始的第一个节点
            return slow;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{4, 6, 7, 8, 11, 13, 17, 21, 22, 26, 28, 31, 35, 39, 41, 42, 46, 50, 54, 59, 60, 65, 67, 71, 74, 76, 78, 83, 85, 86, 87, 88, 93, 98, 102, 106, 108, 110, 111, 116, 119, 123, 125, 126, 129, 132, 136, 137, 140, 144, 146, 151, 155, 156, 157, 159, 164, 165, 169, 172, 177, 178, 183, 186, 190, 193, 194, 198, 203, 205, 207, 210, 214, 218, 221, 226, 227, 231, 232, 237, 239, 244, 246, 251, 253, 255, 260, 264, 268, 270, 271, 275, 279, 282, 284, 286, 290, 292, 293, 296, 300, 301, 303, 304, 307, 309, 314, 315, 317, 318, 320, 324, 328, 332, 333, 337, 340, 342, 345, 347, 351, 356, 357, 361, 365, 369, 372, 377, 381, 384, 386, 391, 396, 398, 401, 404, 407, 412, 417, 419, 424, 426, 427, 429, 431, 433, 435, 440, 443, 445, 449, 453, 455, 457, 462, 465, 466, 469, 473, 475, 480, 482, 487, 492, 496, 499, 504, 507, 510, 513, 514, 516, 519, 524, 525, 526, 529, 534, 538, 542, 546, 547, 551, 552, 553, 558, 562, 563, 564, 567, 568, 571, 576, 579, 581, 586, 587, 590, 593, 596, 600, 602, 607, 611, 613, 618, 621, 624, 628, 631, 636, 638, 639, 641, 643, 648, 650, 653, 657, 658, 661, 666, 670, 674, 675, 676, 680, 682, 685});
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head.next;
        System.out.println(getLoopStart(head));
    }
}
