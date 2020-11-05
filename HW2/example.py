import math
A=[2,-10,5,3,8,-5,-1]

def WHATDOIDO( left, right):
    if left==right:
        if A[left]<0:
            return (0, 0, 0, A[left])
        else:
            return (A[left], A[left], A[left], A[left])

    if left<right:
        m = math.floor((left+right)/2)
        (lmaxsum, llmaxsum, lrmaxsum, lsum) = WHATDOIDO(left, m)
        (rmaxsum, rlmaxsum, rrmaxsum, rsum) = WHATDOIDO(m+1, right)
        maxsum = max(max(lmaxsum, rmaxsum), lrmaxsum+rlmaxsum)
        leftalignedmaxsum = max(llmaxsum, lsum+rlmaxsum)
        rightalignedmaxsum = max(rrmaxsum, lrmaxsum+rsum)
        sum1 = lsum+rsum
        return (maxsum, leftalignedmaxsum, rightalignedmaxsum, sum1)

print(WHATDOIDO(0,6))