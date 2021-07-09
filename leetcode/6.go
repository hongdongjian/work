package main

import "fmt"

/**
abcde
a.*a
*/
func isMatch(s string, p string) bool {
	l1 := len(s)
	l2 := len(p)
	i := 0
	j := 0
	for {
		if i >= l1 || j >= l2 {
			break
		}
		if s[i] == p[j] {
			i++
			j++
			continue
		} else {
			if p[j] == '.' {
				i++
				j++
			} else if p[j] == '*' {
				if p[j-1] == '.' {
					l3 := l2 - j - 1
					i = l1 - l3
					j++
				} else {
					if p[j-1] == s[i] {
						i++
						j++
					} else {
						return false
					}
				}
			} else {
				if j+1 < l2 && p[j+1] == '*' {
					j += 2
				} else {
					return false
				}

			}
		}
	}
	return (i == l1) && (j == l2)
}

func main() {
	fmt.Println(isMatch("aaa", "ab*a*c*a"))
}
