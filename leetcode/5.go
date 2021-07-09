package main

import (
	"fmt"
)

// 暴力
//func longestPalindrome(s string) string {
//	l := len(s)
//	max := 1
//	k := 0
//	for i := 0; i < l; i++ {
//		for j := l - 1; j >= i; j-- {
//			a := i
//			b := j
//			c := j - i + 1
//			for {
//				if a < 0 || a > l-1 || b < 0 || b > l-1 {
//					c = -1
//					break
//				}
//				if s[a] != s[b] {
//					c = -1
//					break
//				}
//				if a >= b {
//					break
//				}
//				a++
//				b--
//			}
//			if c > max {
//				max = c
//				k = i
//			}
//		}
//	}
//	return s[k : k+max]
//}

// 动态规划
/**
if j>i:
db[i][j] = dp[i+1][j-1] ^ s[i+1]==s[j-1]
dp[i][i] = 1
dp[i][i+1] = s[i]==s[i+1]
*/
func longestPalindrome(s string) string {
	l := len(s)
	dp := make([][]int, l)
	for i := 0; i < l; i++ {
		dp[i] = make([]int, l)
		for j := 0; j < l; j++ {
			dp[i][j] = 1
		}
	}
	max := 1
	begin := 0
	for k := 2; k <= l; k++ {
		for i := 0; i < l; i++ {
			j := k + i - 1
			if j >= l {
				break
			}
			if s[i] != s[j] {
				dp[i][j] = 0
			} else {
				if i+1 == j {
					dp[i][j] = 1
				} else {
					dp[i][j] = dp[i+1][j-1]
				}
			}
			if dp[i][j] == 1 && k > max {
				max = k
				begin = i
			}
		}
	}
	return s[begin : max+begin]
}

func main() {
	fmt.Println(longestPalindrome("a"))
}
