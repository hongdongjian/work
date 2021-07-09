package main

import "fmt"

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	len1 := len(nums1)
	len2 := len(nums2)
	if len1+len2 == 1 {
		if len1 == 1 {
			return float64(nums1[0])
		} else if len2 == 1 {
			return float64(nums2[0])
		}
	}
	len3 := (len1 + len2) / 2
	s1 := len3 + 1
	s2 := len3 + 1
	if (len1+len2)%2 == 0 {
		s1 = len3
	}
	i := 0
	j := 0
	l := 0
	k1 := 0.0
	k2 := 0.0
	for {
		f := 0
		l++
		if i < len1 && j < len2 {
			if nums1[i] <= nums2[j] {
				i++
			} else {
				j++
				f = 1
			}
		} else if i < len1 {
			i++
		} else {
			j++
			f = 1
		}
		d := 0
		if f == 1 {
			d = nums2[j-1]
		} else {
			d = nums1[i-1]
		}
		if l%2 == 1 {
			k1 = float64(d)
		} else {
			k2 = float64(d)
		}
		if s1 == s2 {
			if l == s1 {
				if l%2 == 1 {
					k2 = k1
				} else {
					k1 = k2
				}

				break
			}
		} else {
			if l == s2 {
				break
			}
		}
	}
	return (k1 + k2) / 2.0
}

func main4() {
	fmt.Println(findMedianSortedArrays([]int{1, 3}, []int{2}))
}
