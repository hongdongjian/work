package main

func twoSum(nums []int, target int) []int {
	result := make([]int, 2)
	//result := []int{0, 1}
	len := len(nums)
	f := 1
	for i:=0; i<len && f == 1; i++ {
		for j:=i+1; j<len && f == 1; j++ {
			if nums[i] + nums[j] == target {
				result[0] = i
				result[1] = j
				f = 0
			}
		}
	}
	return result
}

func main1()  {
	print(twoSum([]int{3,1,2}, 4))
}


