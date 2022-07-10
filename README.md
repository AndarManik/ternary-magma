# ternary-magma
> An approach to understanding neural networks categorically;

# Magma

https://en.m.wikipedia.org/wiki/Magma_(algebra)

- A Magma is a set combined with a closed binary operation
- unlike a Group a Magma has no additional structure
- A Magma with a set S can be imaged as [s0, s1, ..., iN^2] where s's are elements of S and N is the size of I;

# NeuroStructure

https://en.m.wikipedia.org/wiki/Neural_network

- A NeuroStructure is a a learnable model with a set of parameters with a Neuronal Structure;
- The Neuronal Structure N can be represented as [a0, a1, ... , 1] where a's are Natural numbers including 0 and a0 <= 2;
- A NeuroStructure with a set I can be imaged as [i0, i1, ..., iN^2] where i's are the outputs and N is the size of I;

# Convergence
- A NeuroStructure is Convergent on a Magma if the trained NeuroStructure replicates the binary operation of the magma;
- A NeuroStructure N Converges on a Magma a, is notated N(m) = T;
- A NeuroStructure N does not Converge on a Magma m, is notated N(m) = F;

# NeuroMorphism of Magmas
Magmas a, b are NeuroMorphic if for all NeuroStructures N,
N(a) = N(b)

# NeuroMorphism of NeuroStructures
NeuroStructures N, M are NeuroMorphic if for all Magmas a of set I,
N(a) =  M(a)

# Hierarchy of Magmas
Magma a, b are a > b if there exists a NeuroStructure N such that,
N(a) = F and N(b) = T

# Hierarchy of NeuroStructures;
- NeuroStructures N, M ard N > M if there exists a Magma a such that,
- N(a) = T and M(a) = F

# Example 1
- Consider the binary Set {-1, 1} for Magmas

Magmas [-1,-1,-1,-1] and [1,1,1,1] are NeuroMorphic
NeuroStructure [1] converges on these magmas nm,
Lets call this pairing A

Magmas  [-1, 1,-1, 1] [-1,-1, 1, 1] [ 1, 1,-1,-1] [ 1,-1, 1,-1] are NeuroMorphic
NeuroStructure [1,1] converges on these magmas
lets call this pairing B

Magmas [-1,-1,-1, 1] [-1,-1, 1,-1] [-1, 1,-1,-1] [-1, 1, 1, 1]
       [ 1,-1,-1,-1] [ 1,-1, 1, 1] [ 1, 1,-1, 1] [ 1, 1, 1,-1] are NeuroMorphic
NeuroStructure [2, 1] converges on these magmas
Lets call this pairing C

Magmas [-1, 1, 1,-1] and [ 1,-1,-1, 1] are NeuroMorphic
NeuroStructure [2, 2, 1] and all larger NeuroStructures converges on these magmas
Lets call this pairing D

D > C > B > A

A is independent of both variables
B is dependent on one variable
C is dependent on both variables
D is dependent on both variables with property (a,b) = (a,c) <=> b = c

Category of NeuroStructures under NeuroMorphism
to be continued...
