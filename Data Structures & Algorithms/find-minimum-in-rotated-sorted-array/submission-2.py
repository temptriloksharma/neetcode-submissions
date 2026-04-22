class Solution:
    def findMin(self, nums: List[int]) -> int:
        left = 0
        right = len(nums)-1

        while left < right:
            if nums[left] > nums[right]:
                mid = (left + right)//2
                print(left, right, mid, nums[left], nums[right], nums[mid])
                if nums[mid] < nums[right]:
                    right = mid
                else:
                    left = mid+1
            else:
                return nums[left]

        return nums[left]

        