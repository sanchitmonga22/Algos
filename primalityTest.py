import random

"""
Doing modular exponentiation
This function returns the value of a^y mod p
"""
def power(a, y, p):
	res = 1
	a = a % p
	while (y > 0):
		if (y & 1):
			res = (res * a) % p
		y = y>>1
		a = (a * a) % p
	return res

"""
This is the main millerTest function that generates a random value of between 2 and the n-2,
and uses the algorithm to check whether or not the given number is probably prime
"""
def miillerTest(d, n):
	# generating random a between 2 and n-2
	a = 2 + random.randint(1, n - 4)

	# calculating a^d % n for the first iteration
	x = power(a, d, n)
	# checking whether it's -1 not 1, then it is likely prime
	if (x == 1 or x == n - 1):
		return True

	# if the first iteration is not 1 or -1, then squaring it, and checking whether or not it is -1 continuously, then it will be True
	while (d != n - 1):
		x = (x * x) % n
		d *= 2
		if (x == 1):
			return False
		if (x == n - 1):
			return True
	return False

"""
This function calls the millerTest function k number of times and runs the algorithm and returns the number of times
the algorithm found the input number n to be likely prime
"""
def isPrime(n,k):
    d= n-1
    while(d%2==0):
        d//=2
    trueCounter=0

	# running the test for random values of a in the algorithm for k number of times
    for i in range(k):
        if(miillerTest(d,n)):
            trueCounter+=1
    return trueCounter

# using the k value for the precision upto 3 decimal points for the probability percentage
k=100000
m={}
#looping from the start to end of the given range
for n in range(105001,115000,2):
    prob=isPrime(n,k)/k
    if(prob!=0 and prob !=1):
        m[n]=prob
    print("probability of "+str(n)+" = "+str(prob))
#sorting the items according to the values in the map and printing them
print(sorted(m.items(),key=lambda kv:(kv[1],kv[0])))

# last 10 values printed or the numbers with the highest probability
"""
(109061, 0.16481) or 16.481%
(111361, 0.10971) or 10.971%
(113401, 0.09912) or 9.912%
(112141, 0.07091) or 7.091%
(105163, 0.04969) or 4.969%
(113527, 0.04863) or 4.863%
(114589, 0.03880) or 3.880%
(114211, 0.03630) or 3.630%
(111841, 0.02771) or 2.771%
(114247, 0.01629) or 1.629%
"""



"""
[(105005, 1e-05), (105053, 1e-05), (105473, 1e-05), (105475, 1e-05), (105595, 1e-05), (105641, 1e-05), (105745, 1e-05), (105893, 1e-05), (106049, 1e-05), (106183, 1e-05), (106237, 1e-05), (106333, 1e-05), (106477, 1e-05), (107081, 1e-05), (107113, 1e-05), (107215, 1e-05), (107329, 1e-05), (107957, 1e-05), (108197, 1e-05), (108715, 1e-05), (108797, 1e-05), (109087, 1e-05), (109309, 1e-05), (109495, 1e-05), (109729, 1e-05), (109805, 1e-05), (110065, 1e-05), (110093, 1e-05), 
(110633, 1e-05), (111097, 1e-05), (111381, 1e-05), (111425, 1e-05), (111619, 1e-05), (112009, 1e-05), (112817, 1e-05), (113335, 1e-05), (113407, 1e-05), (113605, 1e-05), (114331, 1e-05), (114341, 1e-05), (114461, 1e-05), (114517, 1e-05), (114557, 1e-05), (105079, 2e-05), (105605, 2e-05), (105703, 2e-05), (105713, 2e-05), (105757, 2e-05), (105845, 2e-05), (105859, 2e-05), (106255, 2e-05), (106435, 2e-05), (106465, 2e-05), (106585, 2e-05), (106741, 2e-05), (106765, 2e-05), (106793, 2e-05), (106901, 2e-05), (107203, 2e-05), (107305, 2e-05), (107341, 2e-05), (107633, 2e-05), (107677, 2e-05), (107785, 2e-05), (108145, 2e-05), (108245, 2e-05), (108425, 2e-05), (108545, 2e-05), (108749, 2e-05), (108985, 2e-05), (108989, 2e-05), (108997, 2e-05), (109165, 2e-05), (109205, 2e-05), (109795, 2e-05), (109925, 2e-05), (109939, 2e-05), (110005, 2e-05), (110113, 2e-05), (110211, 2e-05), (110669, 2e-05), (110845, 2e-05), (110959, 2e-05), (111077, 2e-05), (111199, 2e-05), (111601, 2e-05), (111745, 2e-05), (111979, 2e-05), (112057, 2e-05), (112135, 2e-05), (112165, 2e-05), (112205, 2e-05), (112325, 2e-05), (112375, 2e-05), (112693, 2e-05), (112697, 2e-05), (112735, 2e-05), (112765, 2e-05), (112865, 2e-05), (113065, 2e-05), (113071, 2e-05), (113389, 2e-05), (113509, 2e-05), (113593, 2e-05), (113633, 2e-05), (113785, 2e-05), (113789, 2e-05), (114305, 2e-05), (114353, 2e-05), (114565, 2e-05), (114881, 2e-05), (105127, 3e-05), (105133, 3e-05), (105209, 3e-05), (105247, 3e-05), (105259, 3e-05), (105725, 3e-05), (105823, 3e-05), (105841, 3e-05), (105901, 3e-05), (105949, 3e-05), (106099, 
3e-05), (106249, 3e-05), (106325, 3e-05), (106345, 3e-05), (106517, 3e-05), (106705, 3e-05), (106805, 3e-05), (107117, 3e-05), (107333, 3e-05), (107413, 3e-05), (107561, 3e-05), (107573, 3e-05), (107755, 3e-05), (107885, 3e-05), (107945, 3e-05), (108005, 3e-05), (108085, 3e-05), (108257, 3e-05), (108475, 3e-05), (108493, 3e-05), (108535, 3e-05), (108547, 3e-05), (108613, 3e-05), (108619, 3e-05), (108681, 3e-05), (108685, 3e-05), (108741, 3e-05), (109069, 3e-05), (109075, 3e-05), (109085, 3e-05), (109157, 3e-05), (109189, 3e-05), (109217, 3e-05), (109325, 3e-05), (109327, 3e-05), (109369, 3e-05), (109375, 3e-05), (109405, 3e-05), (109465, 3e-05), (109625, 3e-05), (109657, 3e-05), (110021, 3e-05), (110035, 3e-05), (110057, 3e-05), (110095, 3e-05), (110125, 3e-05), (110171, 3e-05), (110185, 3e-05), (110241, 3e-05), (110363, 3e-05), (110365, 3e-05), (110417, 3e-05), (110575, 3e-05), (110653, 3e-05), (110809, 3e-05), (110945, 3e-05), (111067, 3e-05), (111115, 3e-05), (111145, 3e-05), (111175, 3e-05), (111251, 3e-05), (111385, 3e-05), (111445, 3e-05), (111643, 3e-05), (111677, 3e-05), (111727, 3e-05), 
(111737, 3e-05), (111901, 3e-05), (112081, 3e-05), (112105, 3e-05), (112229, 3e-05), (112315, 3e-05), (112345, 3e-05), (112405, 3e-05), (112421, 3e-05), (112495, 3e-05), (112549, 3e-05), (112723, 3e-05), (112745, 3e-05), (112795, 3e-05), (112837, 3e-05), (112885, 3e-05), (112969, 3e-05), (112975, 3e-05), (113047, 3e-05), (113119, 3e-05), (113185, 3e-05), (113405, 3e-05), (113485, 3e-05), (113511, 3e-05), (113515, 3e-05), (113545, 3e-05), (113573, 3e-05), (113665, 3e-05), (113669, 3e-05), (113839, 3e-05), (113845, 3e-05), (113885, 3e-05), (113941, 3e-05), (114037, 3e-05), (114051, 3e-05), (114053, 3e-05), (114121, 3e-05), (114175, 3e-05), (114293, 3e-05), (114325, 3e-05), (114349, 3e-05), (114413, 3e-05), (114497, 3e-05), (114805, 3e-05), (114865, 3e-05), (114965, 3e-05), (105281, 4e-05), (105305, 4e-05), (105343, 4e-05), (105385, 4e-05), (105425, 4e-05), (105775, 4e-05), (105785, 4e-05), (105805, 4e-05), (105857, 4e-05), (105891, 4e-05), (106385, 4e-05), (106565, 4e-05), (106843, 4e-05), (107125, 4e-05), (107165, 4e-05), (107237, 4e-05), (107395, 4e-05), (107405, 4e-05), (107501, 4e-05), (107503, 4e-05), (107549, 4e-05), (107601, 4e-05), (107753, 4e-05), (107845, 4e-05), (107869, 4e-05), (108115, 4e-05), (108125, 4e-05), (108341, 4e-05), (108355, 4e-05), (108409, 4e-05), (108559, 4e-05), (108565, 4e-05), (108625, 4e-05), (108785, 4e-05), (108805, 4e-05), (108809, 4e-05), (108823, 4e-05), (108865, 4e-05), (109021, 4e-05), (109105, 4e-05), (109151, 4e-05), (109345, 4e-05), (109381, 4e-05), (109409, 4e-05), (109417, 4e-05), (109483, 4e-05), (109577, 4e-05), (109757, 
4e-05), (109871, 4e-05), (109885, 4e-05), (109997, 4e-05), (110451, 4e-05), (110465, 4e-05), (110513, 4e-05), (110585, 4e-05), (110695, 4e-05), (110765, 4e-05), (110781, 4e-05), (110785, 4e-05), (110803, 4e-05), (110831, 4e-05), (110981, 4e-05), (111005, 4e-05), (111365, 4e-05), (111685, 4e-05), (111757, 4e-05), (111845, 4e-05), (111877, 4e-05), (111917, 4e-05), (111967, 4e-05), (112025, 4e-05), (112075, 4e-05), (112093, 4e-05), (112265, 4e-05), (112387, 4e-05), (112431, 4e-05), (112477, 4e-05), (112627, 4e-05), (112781, 4e-05), (113261, 4e-05), (113377, 4e-05), (113791, 4e-05), (113827, 4e-05), (113995, 4e-05), (114029, 4e-05), (114085, 4e-05), (114185, 4e-05), (114265, 4e-05), (114283, 4e-05), (114289, 4e-05), (114415, 4e-05), (114499, 4e-05), (114505, 4e-05), (114533, 4e-05), (114581, 4e-05), (114605, 4e-05), (114655, 4e-05), (105065, 5e-05), (105085, 5e-05), (105109, 5e-05), (105231, 5e-05), (105349, 5e-05), (105365, 5e-05), (105415, 5e-05), (105445, 5e-05), (105505, 5e-05), (105521, 5e-05), (105523, 5e-05), (105685, 5e-05), (105763, 5e-05), (105861, 5e-05), (106045, 5e-05), (106075, 5e-05), 
(106117, 5e-05), (106375, 5e-05), (106421, 5e-05), (106445, 5e-05), (106577, 5e-05), (106625, 5e-05), (106641, 5e-05), (106777, 5e-05), (106885, 5e-05), (107189, 5e-05), (107261, 5e-05), (107361, 5e-05), (107605, 5e-05), (107645, 5e-05), (107707, 5e-05), (107861, 5e-05), (107899, 5e-05), (107909, 5e-05), (107969, 5e-05), (108175, 5e-05), (108505, 5e-05), (108725, 5e-05), (108895, 5e-05), (109289, 5e-05), (109435, 5e-05), (109611, 5e-05), (109685, 5e-05), (109687, 5e-05), (109735, 5e-05), (109747, 5e-05), (109813, 5e-05), (109973, 5e-05), (110045, 5e-05), (110081, 5e-05), (110381, 5e-05), (110413, 5e-05), (110515, 5e-05), (110525, 5e-05), (110539, 5e-05), (110551, 5e-05), (110665, 5e-05), (110717, 5e-05), (110839, 5e-05), (110893, 5e-05), (110965, 5e-05), (111061, 5e-05), (111111, 5e-05), (111163, 5e-05), (111305, 5e-05), (111449, 5e-05), (111761, 5e-05), (111861, 5e-05), (111905, 5e-05), (111989, 5e-05), (112073, 5e-05), (112225, 5e-05), (112309, 5e-05), (112519, 5e-05), (112661, 5e-05), (112675, 5e-05), (112709, 5e-05), (112793, 5e-05), (112925, 5e-05), (112961, 5e-05), (113105, 5e-05), (113249, 5e-05), (113705, 5e-05), (113917, 5e-05), (113965, 5e-05), (114109, 5e-05), (114457, 5e-05), (114637, 5e-05), (114775, 5e-05), (105043, 6e-05), (105161, 6e-05), (105485, 6e-05), (105493, 6e-05), (106025, 6e-05), (106063, 6e-05), (106071, 6e-05), (106301, 6e-05), (106493, 6e-05), (106571, 6e-05), (106701, 6e-05), (107225, 6e-05), (107297, 6e-05), (107389, 6e-05), (107481, 6e-05), (107811, 6e-05), (108097, 6e-05), (108241, 6e-05), (108265, 6e-05), (108305, 6e-05), (108919, 
6e-05), (109301, 6e-05), (109851, 6e-05), (109853, 6e-05), (109879, 6e-05), (109901, 6e-05), (109963, 6e-05), (109985, 6e-05), (110181, 6e-05), (110357, 6e-05), (110391, 6e-05), (110517, 6e-05), (110645, 6e-05), (111065, 6e-05), (111157, 6e-05), (111205, 6e-05), (111265, 6e-05), (111371, 6e-05), (111595, 6e-05), (111625, 6e-05), (111725, 6e-05), (111985, 6e-05), (112045, 6e-05), (112371, 6e-05), (112517, 6e-05), (112541, 6e-05), (112705, 6e-05), (112853, 6e-05), (112945, 6e-05), (113001, 6e-05), (113031, 6e-05), (113101, 6e-05), (113165, 6e-05), (113473, 6e-05), (113575, 6e-05), (113585, 6e-05), (113709, 6e-05), (113849, 6e-05), (114019, 6e-05), (114021, 6e-05), (114101, 6e-05), (114163, 6e-05), (114351, 6e-05), (114677, 6e-05), (114725, 6e-05), (114891, 6e-05), (105171, 7e-05), (105185, 7e-05), (105223, 7e-05), (105235, 7e-05), (105381, 7e-05), (105441, 7e-05), (105553, 7e-05), (105569, 7e-05), (106105, 7e-05), (106281, 7e-05), (106315, 7e-05), (106671, 7e-05), (106745, 7e-05), (106971, 7e-05), (107023, 7e-05), (107051, 7e-05), (107421, 7e-05), (107465, 7e-05), (107585, 7e-05), (107991, 7e-05), 
(108171, 7e-05), (108173, 7e-05), (108251, 7e-05), (108281, 7e-05), (108367, 7e-05), (108591, 7e-05), (108795, 7e-05), (108801, 7e-05), (108851, 7e-05), (109161, 7e-05), (109265, 7e-05), (109645, 7e-05), (110225, 7e-05), (110393, 7e-05), (110405, 7e-05), (110481, 7e-05), (110561, 7e-05), (110797, 7e-05), (111013, 7e-05), (111273, 7e-05), (111469, 7e-05), (111557, 7e-05), (111891, 7e-05), (112041, 7e-05), (112301, 7e-05), (112401, 7e-05), (112761, 7e-05), (112829, 7e-05), (113033, 7e-05), (113155, 7e-05), (113751, 7e-05), (113945, 7e-05), (114231, 7e-05), (114433, 7e-05), (114511, 7e-05), (114625, 7e-05), (114835, 7e-05), (114851, 7e-05), (114927, 7e-05), (105431, 8e-05), (105501, 8e-05), (105511, 8e-05), (105547, 8e-05), (105591, 8e-05), (105629, 8e-05), (105645, 8e-05), (105799, 8e-05), (105855, 8e-05), (106131, 8e-05), (106161, 8e-05), (106401, 8e-05), (106709, 8e-05), (106795, 8e-05), (106829, 8e-05), (106985, 8e-05), (107001, 8e-05), (107471, 8e-05), (107539, 8e-05), (107661, 8e-05), (107901, 8e-05), (107905, 8e-05), (108141, 8e-05), (108417, 8e-05), (108471, 8e-05), (108721, 8e-05), (109131, 8e-05), (109181, 8e-05), (109225, 8e-05), (109285, 8e-05), (109571, 8e-05), (109791, 8e-05), (109915, 8e-05), (110545, 8e-05), (110901, 8e-05), (110983, 8e-05), (111321, 8e-05), (111441, 8e-05), (111517, 8e-05), (111771, 8e-05), (111787, 8e-05), (111941, 8e-05), (112063, 8e-05), (112355, 8e-05), (112615, 8e-05), (112933, 8e-05), (113009, 8e-05), (113091, 8e-05), (113825, 8e-05), (113977, 8e-05), (114081, 8e-05), (114111, 8e-05), (114169, 8e-05), (114385, 8e-05), (114411, 
8e-05), (114469, 8e-05), (114817, 8e-05), (114829, 8e-05), (105125, 9e-05), (105141, 9e-05), (105565, 9e-05), (105711, 9e-05), (105741, 9e-05), (106171, 9e-05), (106251, 9e-05), (106573, 9e-05), (106811, 9e-05), (107705, 9e-05), (107871, 9e-05), (108319, 9e-05), (108577, 9e-05), (108703, 9e-05), (109009, 9e-05), (109191, 9e-05), (109615, 9e-05), (109777, 9e-05), (110433, 9e-05), (111277, 9e-05), (111561, 9e-05), (112231, 9e-05), (113237, 9e-05), (113345, 9e-05), (113597, 9e-05), (113919, 9e-05), (114261, 9e-05), (114771, 9e-05), (105175, 0.0001), (105393, 0.0001), (105435, 0.0001), (105583, 0.0001), (105925, 0.0001), (106361, 0.0001), (106519, 0.0001), (106771, 0.0001), (106821, 0.0001), (106897, 0.0001), (106905, 0.0001), (106989, 0.0001), (107061, 0.0001), (107121, 0.0001), (107143, 0.0001), (107199, 0.0001), (107301, 0.0001), (107423, 0.0001), (108199, 0.0001), (108531, 0.0001), (108601, 0.0001), (108861, 0.0001), (109005, 0.0001), (109333, 0.0001), (109635, 0.0001), (109881, 0.0001), (110055, 0.0001), (110345, 0.0001), (110713, 0.0001), (110811, 0.0001), (110853, 0.0001), (111073, 0.0001), (111391, 0.0001), (111559, 0.0001), (111605, 0.0001), (111881, 0.0001), (112099, 0.0001), (112101, 0.0001), (112155, 0.0001), (112357, 0.0001), (112881, 0.0001), (113181, 0.0001), (113271, 0.0001), (113911, 0.0001), (114045, 0.0001), (114127, 0.0001), (114145, 0.0001), (114365, 0.0001), (114397, 0.0001), (114423, 0.0001), (114595, 0.0001), (114869, 0.0001), (105289, 0.00011), (105411, 0.00011), (105559, 0.00011), (106265, 0.00011), (106267, 0.00011), (106309, 0.00011), (106483, 0.00011), (106645, 0.00011), (107149, 0.00011), (107191, 0.00011), (108165, 0.00011), (108331, 0.00011), (108485, 0.00011), (108937, 0.00011), (108953, 0.00011), (109101, 0.00011), (109231, 0.00011), (109251, 0.00011), (109531, 0.00011), (109549, 0.00011), (109711, 0.00011), (110071, 0.00011), (110173, 0.00011), 
(110203, 0.00011), (110509, 0.00011), (110891, 0.00011), (111019, 0.00011), (111081, 0.00011), (111589, 0.00011), (112625, 0.00011), (112729, 0.00011), (113323, 0.00011), (113331, 0.00011), (113587, 0.00011), (113835, 0.00011), (114205, 0.00011), (114441, 0.00011), (114559, 0.00011), (114811, 0.00011), (114931, 0.00011), (105025, 0.00012), (105183, 0.00012), (105427, 0.00012), (106027, 0.00012), (106339, 0.00012), (106351, 0.00012), (106399, 0.00012), (106855, 0.00012), (106939, 0.00012), (107167, 0.00012), (107521, 0.00012), (109509, 0.00012), (109543, 0.00012), (109759, 0.00012), (110265, 0.00012), (110299, 0.00012), (110475, 
0.00012), (110691, 0.00012), (110725, 0.00012), (110929, 0.00012), (111185, 0.00012), (112271, 0.00012), (112407, 0.00012), (112441, 0.00012), (112465, 0.00012), (112509, 0.00012), (112591, 0.00012), (112609, 0.00012), (112639, 0.00012), (112747, 0.00012), (113107, 0.00012), (113205, 0.00012), (113275, 0.00012), (113347, 0.00012), (113503, 0.00012), (113765, 0.00012), (113815, 0.00012), (114171, 0.00012), (114709, 0.00012), (114793, 0.00012), (114853, 0.00012), (114979, 0.00012), (105353, 0.00013), (105355, 0.00013), (105571, 0.00013), (105631, 0.00013), (105637, 0.00013), (105847, 0.00013), (106423, 0.00013), (106891, 0.00013), (107353, 0.00013), (107419, 0.00013), (107443, 0.00013), (107485, 0.00013), (107749, 0.00013), (107781, 0.00013), (107803, 0.00013), (107953, 0.00013), (108469, 0.00013), (108931, 0.00013), (109003, 0.00013), (109489, 0.00013), (110591, 0.00013), (110851, 0.00013), (111223, 0.00013), (111319, 0.00013), (111523, 0.00013), (111583, 0.00013), (111937, 0.00013), (112189, 0.00013), (112195, 0.00013), (112483, 0.00013), (112633, 0.00013), (112873, 0.00013), (113479, 0.00013), (114765, 0.00013), (114787, 0.00013), (114925, 0.00013), (114969, 0.00013), (105007, 0.00014), (105091, 0.00014), (105113, 0.00014), (105811, 0.00014), (105831, 0.00014), (106015, 0.00014), (106081, 0.00014), (106177, 0.00014), (106825, 0.00014), (107059, 0.00014), (107293, 0.00014), (107359, 0.00014), (107593, 0.00014), (107737, 0.00014), (107977, 0.00014), (108157, 0.00014), (108325, 0.00014), (108763, 0.00014), (108787, 0.00014), (109209, 0.00014), (109585, 0.00014), (109693, 0.00014), (109699, 0.00014), (109951, 0.00014), (109957, 0.00014), (110089, 0.00014), (110215, 0.00014), (110425, 0.00014), (110837, 0.00014), (110895, 0.00014), (110935, 0.00014), (111025, 0.00014), (111181, 0.00014), (111397, 0.00014), (111849, 0.00014), (111895, 0.00014), (111925, 0.00014), (112777, 0.00014), (112825, 0.00014), (113251, 0.00014), (113281, 0.00014), (113365, 0.00014), (113617, 0.00014), (113833, 0.00014), (114133, 0.00014), (114337, 0.00014), (114373, 0.00014), (114439, 0.00014), (114541, 0.00014), (105439, 0.00015), (105625, 0.00015), (105889, 0.00015), (106313, 0.00015), (106567, 0.00015), (107155, 0.00015), (107275, 0.00015), (107619, 0.00015), (107635, 0.00015), (108259, 0.00015), (108745, 0.00015), (109081, 0.00015), (109207, 0.00015), (109855, 0.00015), 
(110131, 0.00015), (110353, 0.00015), (110683, 0.00015), (111399, 0.00015), (111421, 0.00015), (111475, 0.00015), (111547, 0.00015), (111657, 0.00015), (111769, 0.00015), (112255, 0.00015), (113305, 0.00015), (113415, 0.00015), (113743, 0.00015), (113863, 0.00015), (113869, 0.00015), (113971, 0.00015), (114025, 0.00015), (114027, 0.00015), (114271, 0.00015), (114607, 0.00015), (114929, 0.00015), (105015, 0.00016), (105193, 0.00016), (105487, 0.00016), (106327, 0.00016), (106393, 0.00016), (106525, 0.00016), (106687, 0.00016), (106737, 0.00016), (106813, 0.00016), (107233, 0.00016), (107383, 0.00016), (107653, 0.00016), (107989, 
0.00016), (108073, 0.00016), (108151, 0.00016), (108205, 0.00016), (108207, 0.00016), (108313, 0.00016), (108595, 0.00016), (108853, 0.00016), (109093, 0.00016), (109099, 0.00016), (109867, 0.00016), (110341, 0.00016), (110485, 0.00016), (110761, 0.00016), (111007, 0.00016), (111307, 0.00016), (111499, 0.00016), (112039, 0.00016), (112463, 0.00016), (113191, 0.00016), (114139, 0.00016), (114235, 0.00016), (114445, 0.00016), (114721, 0.00016), (114877, 0.00016), (114919, 0.00016), (105103, 0.00017), (105793, 0.00017), (107179, 0.00017), (107299, 0.00017), (107317, 0.00017), (107575, 0.00017), (107743, 0.00017), (108253, 0.00017), (108451, 0.00017), (108585, 0.00017), (109027, 0.00017), (109077, 0.00017), (109143, 0.00017), (109177, 0.00017), (110107, 0.00017), (110329, 0.00017), (110719, 0.00017), (110941, 0.00017), (111035, 0.00017), (111233, 0.00017), (111739, 0.00017), (111817, 0.00017), (111889, 0.00017), (111961, 0.00017), (112091, 0.00017), (112411, 0.00017), (112903, 0.00017), (113113, 0.00017), (113369, 0.00017), (113533, 0.00017), (113565, 0.00017), (114307, 0.00017), (114937, 0.00017), (105717, 0.00018), (105973, 0.00018), (106889, 0.00018), (106969, 0.00018), (107107, 0.00018), (107545, 0.00018), (107611, 0.00018), (107629, 0.00018), (107955, 0.00018), (108487, 0.00018), (108925, 0.00018), (108941, 0.00018), (109249, 0.00018), (109319, 0.00018), (109393, 0.00018), (109737, 0.00018), (110029, 0.00018), (110599, 0.00018), (111283, 0.00018), (111349, 0.00018), (111655, 0.00018), (111811, 0.00018), (112003, 0.00018), (112351, 0.00018), (112399, 0.00018), (112443, 0.00018), (112525, 0.00018), (112789, 0.00018), (112993, 0.00018), (113431, 0.00018), (113629, 0.00018), (113725, 0.00018), (113887, 0.00018), (114667, 0.00018), (114703, 0.00018), (114885, 0.00018), (105689, 0.00019), (106225, 0.00019), (106409, 0.00019), (106481, 0.00019), (106723, 0.00019), (106837, 0.00019), (107295, 0.00019), (107425, 0.00019), (107527, 0.00019), (107725, 0.00019), (107797, 0.00019), (107965, 0.00019), (108043, 0.00019), (108607, 0.00019), (108667, 0.00019), (108813, 0.00019), (109539, 0.00019), (109753, 0.00019), (110635, 0.00019), (110833, 0.00019), (110905, 0.00019), (111379, 0.00019), (111433, 0.00019), (111671, 0.00019), (112891, 0.00019), (113125, 0.00019), (113939, 0.00019), (114475, 0.00019), (114907, 0.00019), (106999, 0.0002), (107017, 0.0002), (107257, 0.0002), (107731, 0.0002), (108955, 0.0002), (109447, 0.0002), (109501, 0.0002), (109669, 0.0002), (109825, 0.0002), (110305, 0.0002), (110521, 0.0002), (111823, 0.0002), (112417, 0.0002), (112579, 0.0002), (112617, 0.0002), (112987, 0.0002), (113005, 0.0002), (113239, 0.0002), (113857, 0.0002), (114313, 0.0002), (114619, 0.0002), (114871, 0.0002), (106819, 0.00021), (107947, 0.00021), (108137, 0.00021), (108817, 0.00021), (108915, 0.00021), (109399, 0.00021), (109817, 0.00021), (109837, 0.00021), (110041, 0.00021), (110637, 0.00021), (111161, 0.00021), (112669, 0.00021), (113035, 0.00021), (114961, 0.00021), (105777, 0.00022), (108913, 0.00022), (109603, 0.00022), (109681, 0.00022), (110971, 0.00022), (111139, 0.00022), (112531, 0.00022), (105403, 0.00023), (105961, 0.00023), 
(106285, 0.00023), (106711, 0.00023), (110489, 0.00023), (112201, 0.00023), (112285, 0.00023), (113367, 0.00023), (113895, 0.00023), (114453, 0.00023), (114895, 0.00023), (107911, 0.00024), (109627, 0.00024), (114153, 0.00024), (114583, 0.00024), (105217, 0.00025), (105241, 0.00025), (105881, 0.00025), (106405, 0.00025), (107371, 0.00025), (108113, 0.00025), (108829, 0.00025), (109429, 0.00025), (109617, 0.00025), (110011, 0.00025), (110857, 0.00025), (111695, 0.00025), (112167, 0.00025), (112685, 0.00025), (114323, 0.00025), (114631, 0.00025), (106887, 0.00026), (107135, 0.00026), (107365, 0.00026), (109921, 0.00026), (110249, 
0.00026), (111565, 0.00026), (111673, 0.00026), (112015, 0.00026), (105117, 0.00027), (105721, 0.00027), (109045, 0.00027), (111401, 0.00027), (111703, 0.00027), (112251, 0.00027), (114115, 0.00027), (114745, 0.00027), (106965, 0.00028), (107271, 0.00028), (108373, 0.00028), (108511, 0.00028), (111189, 0.00028), (111873, 0.00028), (105061, 0.00029), (105145, 0.00029), (107665, 0.00029), (112489, 0.00029), (106513, 0.0003), (107291, 0.0003), (108385, 0.0003), (109705, 0.0003), (111865, 0.0003), (112645, 0.0003), (114367, 0.0003), (108665, 0.00031), (112801, 0.00031), (105639, 0.00032), (105937, 0.00032), (110245, 0.00032), (110755, 0.00032), (111505, 0.00032), (111661, 0.00032), (113611, 0.00032), (107041, 0.00033), (108221, 0.00033), (108655, 0.00033), (108775, 0.00033), (110705, 0.00033), (111331, 0.00033), (111481, 0.00033), (111805, 0.00033), (111991, 0.00033), (114633, 0.00033), (114801, 0.00033), (106945, 0.00034), (114791, 0.00034), (105985, 0.00035), (106165, 0.00035), (107809, 0.00035), (109057, 0.00035), (105261, 0.00036), (106271, 0.00036), (107701, 0.00036), (111541, 0.00036), (111929, 0.00036), (112321, 0.00036), (107245, 0.00037), (113029, 0.00037), (113641, 0.00037), (114361, 0.00037), (114431, 0.00037), (107281, 0.00038), (110143, 0.00038), (112211, 0.00038), (112423, 0.00038), (112435, 0.00038), (105941, 0.00039), (107497, 0.00039), (111001, 0.00039), (111085, 0.00039), (114301, 0.00039), (105301, 0.0004), (106555, 0.0004), (107551, 0.0004), (107921, 0.0004), (108671, 0.0004), (110209, 0.0004), (113311, 0.0004), (114049, 0.0004), (114673, 0.0004), 
(114731, 0.0004), (114741, 0.0004), (106609, 0.00041), (107043, 0.00041), (107863, 0.00041), (112273, 0.00041), (112813, 0.00041), (113355, 0.00041), (113801, 0.00041), (114131, 0.00041), (109461, 0.00042), (111853, 0.00042), (113531, 0.00042), (106873, 0.00043), (108277, 0.00043), (110227, 0.00043), (110741, 0.00043), (113953, 0.00043), (106231, 0.00044), (106241, 0.00044), (106931, 0.00044), (107569, 0.00044), (107591, 0.00044), (108181, 0.00044), (108581, 0.00044), (114079, 0.00044), (114911, 0.00044), (105661, 0.00045), (105671, 0.00045), (106603, 0.00045), (107887, 0.00045), (108147, 0.00045), (108361, 0.00045), (108901, 0.00045), (109351, 0.00045), (110191, 0.00045), (110383, 0.00045), (111011, 0.00045), (111151, 0.00045), (112333, 0.00045), (112567, 0.00045), (113671, 0.00045), (105307, 0.00046), (105589, 0.00046), (105611, 0.00046), (107401, 0.00046), (107779, 0.00046), (108121, 0.00046), (108397, 0.00046), (108641, 0.00046), (108701, 0.00046), (110231, 0.00046), (111221, 0.00046), (113861, 0.00046), (114733, 0.00046), (109039, 0.00047), (109237, 0.00047), (111289, 0.00047), (111709, 0.00047), (112177, 0.00047), (105157, 0.00048), (106153, 0.00048), (106933, 0.00048), (107065, 0.00048), (108911, 0.00048), (111313, 0.00048), (111551, 0.00048), (111553, 0.00048), (112985, 0.00048), (113197, 0.00048), (113421, 0.00048), (114253, 0.00048), (105313, 0.00049), (108025, 0.00049), (109931, 0.00049), (110257, 0.00049), (112147, 0.00049), (105049, 0.0005), (106601, 0.0005), (108673, 0.0005), (109573, 0.0005), (110047, 0.0005), (110773, 0.0005), (112981, 0.0005), (113677, 0.0005), (113893, 0.0005), (106729, 0.00051), (107151, 0.00051), (108589, 0.00051), (109993, 0.00051), (110121, 0.00051), (110471, 0.00051), (110887, 0.00051), (112369, 0.00051), (112791, 0.00051), (113293, 0.00051), (113659, 0.00051), (105151, 0.00052), (106021, 0.00052), (106093, 0.00052), (106717, 0.00052), 
(106951, 0.00052), (108307, 0.00052), (108349, 0.00052), (110467, 0.00052), (110671, 0.00052), (113581, 0.00052), (105781, 0.00053), (109511, 0.00053), (111201, 0.00053), (111343, 0.00053), (112911, 0.00053), (114751, 0.00053), (107695, 0.00054), (109135, 0.00054), (111971, 0.00054), (112957, 0.00054), (108283, 0.00055), (112597, 0.00055), (112915, 0.00055), (109213, 0.00056), (109241, 0.00056), (109563, 0.00056), (110677, 0.00056), (113269, 0.00056), (108345, 0.00057), (113449, 0.00057), (111037, 0.00058), (112491, 0.00058), (114521, 0.00058), (105709, 0.00059), (107029, 0.0006), (109129, 0.0006), (110177, 0.00061), (114381, 0.00061), (106069, 0.00062), (108661, 0.00062), (109123, 0.00062), (112085, 0.00062), (114529, 0.00062), (110995, 0.00063), (113701, 0.00064), (109969, 0.00065), (110401, 0.00065), (111809, 0.00065), (105865, 0.00067), (108693, 0.00067), (109765, 0.00068), (109565, 0.0007), (109945, 0.00071), (106195, 0.00074), (110409, 0.00074), (111281, 0.00074), (107729, 0.00075), (109633, 0.00075), (106381, 0.00076), (110657, 0.00076), (108209, 0.00077), (108889, 0.00077), (111075, 0.00077), (105265, 0.00078), (112317, 0.00078), (112651, 0.00078), (110993, 0.00079), (113221, 0.00079), (106499, 0.0008), (113713, 0.0008), (108001, 0.00081), (109525, 0.00081), (107255, 0.00082), (111679, 0.00082), (114449, 0.00082), (106429, 0.00083), (110867, 0.00083), (113425, 0.00084), (113441, 0.00085), (107311, 0.00086), (113303, 0.00087), (113387, 0.00087), (106691, 0.00088), (106981, 0.00088), (111413, 0.00088), (105391, 0.00089), (105029, 0.0009), (109271, 0.0009), (111629, 0.0009), (113219, 0.0009), (108431, 0.00091), (111325, 0.00091), (112393, 0.00091), (109243, 0.00093), (107759, 0.00094), (110001, 0.00095), (106369, 0.00098), (105995, 0.00099), (109607, 0.00099), (108407, 0.00113), (110815, 0.00113), (106639, 0.00114), (114245, 0.00114), (114955, 0.00114), (107801, 0.00117), (111101, 0.00122), (110331, 0.00125), (112381, 0.00128), (105697, 0.0013), (112879, 0.0013), (109649, 0.00131), (109459, 0.00132), (113923, 0.00134), (109117, 0.00135), (112537, 0.00135), (109801, 0.00136), (105787, 0.00137), (106107, 0.00137), (105013, 0.00138), (105469, 0.00138), (107161, 0.00138), (114985, 0.00138), (111511, 0.00139), (114391, 0.00139), (106039, 0.0014), (107461, 0.00141), (108019, 0.00142), (108091, 0.00142), (107263, 0.00146), (113563, 0.00146), (107587, 0.00147), (111169, 0.00147), (108433, 0.00148), (113905, 0.00148), (106507, 0.00149), (110593, 0.0015), (105931, 0.00151), (110137, 0.00153), (111241, 0.00154), (107407, 0.00155), (114823, 0.00157), (105283, 0.00158), (110827, 0.00159), (111763, 0.00159), (111259, 0.00161), (109927, 0.00163), (107659, 0.00165), (107185, 0.00166), (106141, 0.00167), (109513, 0.00172), (105481, 0.00178), (108697, 0.00178), (114191, 0.0019), (108979, 0.00199), (110461, 0.00201), (114427, 0.00203), (106789, 0.00207), (114379, 0.00209), (112897, 0.00211), (107669, 0.0022), (110149, 0.00224), (112021, 0.00225), (114003, 0.00226), (106283, 0.00231), (111631, 0.00232), (112871, 0.00236), (108871, 0.00237), (113873, 0.00245), (109411, 0.00247), (107213, 0.00256), (109291, 0.00256),
(106807, 0.00258), (108837, 0.00273), (113049, 0.00274), (109561, 0.00284), (113569, 0.00291), (112861, 0.003), (106159, 0.00308), (112529, 0.00311), (113737, 0.0032), (105205, 0.00325), (114151, 0.0037), (109771, 0.00379), (114421, 0.00379), (114091, 0.00392), (111691, 0.00394), (108031, 0.00398), (110701, 0.00403), (113077, 0.00414), (109981, 0.00418), (112561, 0.00424), (109261, 0.00429), (107929, 0.00432), (108841, 0.00433), (105121, 0.00437), (111943, 0.00441), (105679, 0.00446), (110953, 0.00451), (106057, 0.00459), (114841, 0.00465), (113257, 0.00468), (112681, 0.0049), (110617, 0.00499), (105877, 0.00536), (110449, 0.00561), (105821, 0.00582), (108691, 0.00646), (113653, 0.00646), (105271, 0.00673), (110371, 0.00677), (113491, 0.00703), (106051, 0.00738), (113821, 0.00753), (107821, 0.00762), (106561, 0.00774), (108403, 0.00826), (110293, 0.00919), (107537, 0.00928), (110309, 0.0095), (105001, 0.00975), (112471, 0.00978), (106289, 0.00993), (107689, 0.01015), (106491, 0.01072), (106597, 0.0108), (113201, 0.01095), (106151, 0.0117), (105851, 0.01205), (109861, 0.01209), (106201, 0.01228), (113521, 0.01228), (108781, 0.0125), (111055, 0.01328), (114247, 0.01629), (111841, 0.02771), (114211, 0.0363), (114017, 0.03677), (114589, 0.0388), (113527, 0.04863), (105163, 0.04969), (112141, 0.07091), (113401, 0.09912), (111361, 0.10971), (109061, 0.16481)]
"""