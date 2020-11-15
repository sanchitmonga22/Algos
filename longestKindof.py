import random
def lis(arr):
    n = len(arr)
    def helper(i,j,k):
        if i>=n-2 or j>=n-1 or k>=n:
            return 0
        ans1 = 0
        if (arr[i]+arr[j])//2 <= arr[k]:
            ans1 = 1+helper(i+1,j+1,k+1)
            #print(str(arr[i])+" i "+str(arr[j])+" j "+str(arr[k])+" k "+str(l)+" L "+str(m))
        ans2 = helper(i+1,j+1,k+1)
        return max(ans1,ans2)
    return helper(0,1,2)
#print(lis([5,6,8,9]))
#print(lis([20,10,5,0,6,4,15,6,9,8]))   #ans: 5,0,6,4,15
#print(lis([266, 9 ,233, 1, 0, 1, 318, 4 ,9, 16, 25, 197, 49 ,64, 275, 100, 121, 647, 205, 735]))
#1,0,1,318
#4,9,16,25,197
#49,64,275
#100,121,647

#print(str(ans1)+" "+str(ans2)+" element at"+str(i)+":"+str(arr[i])+" element at"+str(j)+":"+str(arr[j])+" element at"+str(k)+":"+str(arr[k]))

def lisRecursive(arr):
    n = len(arr)
    def helper(currIndex,previousIndex):
        if currIndex >= n:
            return 0
        including = 0
        if previousIndex == -1 or arr[currIndex] > arr[previousIndex]:
            including = 1+helper(currIndex+1,currIndex)
        excluding = helper(currIndex+1,previousIndex)
        return max(including,excluding)
    return helper(0,-1)
#print(lisRecursive([266, 9 ,233, 1, 0, 1, 318, 4 ,9, 16, 25, 197, 49 ,64, 275, 100, 121, 647, 205, 735]))
#print(lisRecursive([20,10,5,0,6,4,15,6,9,8]))
#print(lisRecursive([5,6,8,9]))


def lisDP(arr):
    n = len(arr)
    OPT = [0 for i in range(n)]
    OPT[0] = 1
    maxLength = 1
    for i in range(1,n):
        OPT[i] = 1
        for j in range(i):
            if arr[i] > arr[j] and OPT[i] <= OPT[j]:
                OPT[i] = 1+OPT[j]
                maxLength = max(maxLength,OPT[i])
    return maxLength
#print(lisDP([266, 9 ,233, 1, 0, 1, 318, 4 ,9, 16, 25, 197, 49 ,64, 275, 100, 121, 647, 205, 735]))
#print(lisDP([20,10,5,0,6,4,15,6,9,8]))
