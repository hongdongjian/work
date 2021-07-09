package main

import "fmt"

/**

 */

func lengthOfLongestSubstring(s string) int {
	list := make([]int, 500)
	for i := 0; i < 500; i++ {
		list[i] = -1
	}
	// 表示不重复子串的起始点
	k := 0
	max := -1
	maxList := make([]int, len(s))
	for i, n := range s {
		// 前面出现过n，并且位置在k的后面或者就在k上，这个时候已经出现重复
		// 不重复子传的起始点在上个字符的位置的后一个位置
		if list[n] >= k {
			k = list[n] + 1
		}
		list[n] = i
		maxList[i] = i - k
		if i - k > max {
			max = i - k
		}
	}
	return max + 1
}

func main3() {
	fmt.Println(lengthOfLongestSubstring(" ^%$#@&*("))
}

//package main
//
//import "fmt"
//
//func lengthOfLongestSubstring(s string) int {
//	list := make([]int, 26)
//	for i:=0;i<26;i++ {
//		list[i] = -1
//	}
//	k := 0
//	maxList := make([]int, len(s))
//	for i,v := range s {
//		n := v - 'a'
//		if list[n] == -1 {
//			maxList[i] = i - k
//			list[n] = i
//		} else if list[n] >= k {
//			k = list[n] + 1
//			for j:=0;j<26;j++ {
//				if list[j] < k {
//					list[j] = -1
//				}
//			}
//			maxList[i] = i - k
//		} else {
//			list[n] = i
//			maxList[i] = i - k
//		}
//
//	}
//	max := -1
//	for _,v := range maxList {
//		if v > max {
//			max = v
//		}
//	}
//	return max + 1
//}
//
//func main()  {
//	fmt.Println(lengthOfLongestSubstring("helxlojk"))
//}
