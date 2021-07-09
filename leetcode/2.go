package main

import (
	"fmt"
	"math/rand"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1 == nil && l2 == nil {
		return new(ListNode)
	}
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}
	l3 := l1
	l4 := l2
	k := 0
	var head *ListNode
	var tail *ListNode
	for {
		if l3 == nil && l4 == nil && k == 0 {
			break
		}
		tmp := new(ListNode)
		num := k
		if l3 != nil {
			num = num + l3.Val
			l3 = l3.Next
		}
		if l4 != nil {
			num = num + l4.Val
			l4 = l4.Next
		}
		tmp.Val = num % 10
		k = num / 10
		if head == nil {
			head = tmp
			tail = tmp
		} else {
			tail.Next = tmp
			tail = tmp
		}
	}
	return head
}

func main2() {

	l := new(ListNode)
	l.Val = rand.Intn(9) + 1
	l1 := l
	for i := 0; i < 10; i++ {
		tmp := new(ListNode)
		tmp.Val = rand.Intn(10)
		l.Next = tmp
		l = tmp
	}

	l = new(ListNode)
	l.Val = rand.Intn(9) + 1
	l2 := l
	for i := 0; i < 10; i++ {
		tmp := new(ListNode)
		tmp.Val = rand.Intn(10)
		l.Next = tmp
		l = tmp
	}

	l3 := addTwoNumbers(l1, l2)
	for {
		fmt.Println(l3.Val)
		if l3.Next == nil {
			break
		}
		l3 = l3.Next
	}
}
