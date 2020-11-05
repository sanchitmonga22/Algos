def mergeSort(arr):
    mid=len(arr)//2
    if mid ==0:
        return 0

    arr1=arr[0:mid]
    arr2=arr[mid:]
    sum1 = mergeSort(arr1)
    sum2 = mergeSort(arr2)
    sum3 = mergeAndCount(arr1,arr2,arr,sum1+sum2)
    return sum3

def mergeAndCount(arr1,arr2,arr,s):
    i=0
    j=0
    k=0
    n = len(arr1)
    li = [0]*(n+1)
    for i in range(n-1,-1,-1):
        li[i] = li[i+1]+arr1[i]

    while i<len(arr1) and j<len(arr2):
        if arr1[i]<arr2[j]:
            arr[k]=arr1[i]
            k+=1
            i+=1
        else:
            arr[k]=arr2[j]
            # calculating the sum of the inversions
            s += li[i] * arr2[j]
            k+=1
            j+=1


    while i<len(arr1):
        arr[k]=arr1[i]
        k+=1
        i+=1
    while j<len(arr2):
        arr[k]=arr2[j]
        k+=1
        j+=1
    return s

n=5
arr=[5 ,6 ,5, 1, 1, 16, 0 ,1 ,11, 2 ]
print('Weighted count is :',mergeSort(arr))