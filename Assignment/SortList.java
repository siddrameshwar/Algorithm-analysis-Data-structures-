/**
 *
 * @author Siddrameshwar Kadagad
 * sxk190071@utdallas.edu
 * 2021491485
	saturday class
 */
public class SortList {
    public static void main(String[] args){   
        System.out.println("Assignment by Siddrameshwar Kadagad");
        ListNode list;
        list = initialisedList();
        System.out.println("List before sorting");
        printList(list);
        System.out.println("List after sorting");
        list = sortList(list);        
        printList(list);
        System.out.print("END");
    }
    public static void printList(ListNode head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();    
    }
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode minimum = head;
        ListNode loopNode = head;
        ListNode loopNode2 = head;
        ListNode prevToMinimum = head;
        ListNode prevToLoopNode = head;
        ListNode temp = null;
        Boolean firstMinima = true;
        while(loopNode != null) {
            minimum = loopNode;
            loopNode2 = loopNode.next;
            while(loopNode2 != null) {
                if(loopNode2.val < minimum.val)
                    minimum = loopNode2;  
                loopNode2 = loopNode2.next;
            }            
            if(minimum != loopNode) {
                if(loopNode != head) {
                    prevToMinimum = loopNode;
                    while(prevToMinimum.next != minimum) {
                        prevToMinimum = prevToMinimum.next;
                    }
                    temp = minimum.next;
                    minimum.next = loopNode;
                    prevToMinimum.next = temp;
                    prevToLoopNode.next = minimum;  
                    prevToLoopNode = minimum;
                    loopNode = minimum.next;
                } else {
                    prevToMinimum = loopNode;
                    while(prevToMinimum.next != minimum) {
                        prevToMinimum = prevToMinimum.next;
                    }            
                    temp = minimum.next;
                    minimum.next = loopNode;
                    prevToMinimum.next = temp;
                    loopNode = minimum.next;
                    prevToLoopNode = minimum;
                }
            } else {
                prevToLoopNode = loopNode;
                loopNode = loopNode.next;
            }
            
            if(firstMinima) {
                head = minimum;
                firstMinima = false;
            }
        }        
        return head;
    }
    
    public static ListNode initialisedList(){
        ListNode list = new ListNode(4);
        
        ListNode temp = new ListNode(19);
        ListNode temp2 = new ListNode(14);
        ListNode temp3 = new ListNode(5);
        ListNode temp4 = new ListNode(-3);
        ListNode temp5 = new ListNode(1);
        ListNode temp6 = new ListNode(8);
        ListNode temp7 = new ListNode(5);
        ListNode temp8 = new ListNode(11);
        ListNode temp9 = new ListNode(15);
        ListNode temp10 = new ListNode(-44);
        ListNode temp11 = new ListNode(555);
        ListNode temp12 = new ListNode(12);
        ListNode temp13 = new ListNode(93);
        ListNode temp14 = new ListNode(-5);
        ListNode temp15 = new ListNode(-11);
        ListNode temp16 = new ListNode(-15);
        list.next = temp;
        temp.next = temp2;
        temp2.next = temp3;
        temp3.next = temp4;
        temp4.next = temp5;
        temp5.next = temp6;
        temp6.next = temp7;
        temp7.next = temp8;
        temp8.next = temp9;
        temp9.next = temp10;
        temp10.next = temp11;
        temp11.next = temp12;        
        temp12.next = temp13;
        temp13.next = temp14;
        temp14.next = temp15;
        temp15.next = temp16;
        temp16.next = null;
        
        return list;
    }

}
