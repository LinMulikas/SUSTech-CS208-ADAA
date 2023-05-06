# CS208 ADAA Homework 1 
# 12012727 Duolei Wang

## Ch1

### 1. False

Considering the case that there is no such pair such that $m, w$ is the best choice for each other. Thus, every perfect matching can't includes a pair satisfying the hypothesis.

### 2. True

Prove it by contradiciton. Considering there exists a perfect matching, for the two people, they are instability of the matching, thus the matching is not stable, which is contradict to our hypothesis.

### 3. No
Here, I choose (b), to give a contradiction to the hypothesis that every programme with audience rating always has a stable schedule.

#### (b)
Considering A = {6, 4, 2}, B = {5, 3, 1}. 

Whether the schedule be, considering the number of B's win, which is less or equal than 2.

Case 2. It's best schedule for B.

Case 1. 
- Choose the programme with audience rating 1, arrange it at the correspoding time of 6 of A. Then choose 5 to 4, 3 to 2, B will get 2 winners.
- Then, it's A's turn. A will choose 6 against to 5, 4 to 3, 2 to 1. Which is same as the begining of Case 1.


Thus in the case, schedule can't be stable.

## Optional

### 8. Yes, there exist case that the order will change.

Considering

m: w > w' > w''
m': w > w'' > w'
m'': w' > w'' > w

and 

w: m'' > m > m'
w': m' > m > m''
w'': m' > m > m''

And male choose their partner in the order (m, m', m'')

#### Real result.
1. m choose w, w accept. 
   {(m, w)}
2. m' try w, w reject. 
   m' try w'', w'' accept.
   {(m, w), (m', w'')}
3. m'' try w', w accpet.
   {(m, w), (m', w''), (m'', w')}

#### After w's lying.
1. m choose w, w accept.
   {(m, w)}
2. m' try w, w accept, m free.
   {(m', w)}
3. m try w', w' accept.
   {(m', w), (m, w')}
4. m'' try w', w' reject. 
   m'' try w', w' reject. 
   m'' try w, w accept, m' free.
   {(m'', w), (m, w')}
5. m' try w'', w'' accept.
   {(m'', w), (m, w'), (m', w'')}

Thus, it can change the result when all the female that before w in (m'')'s preference list has listed m'' in a lower order than the m, m'.