def latest(scores):
    n = len(scores)
    return scores[n-1]

def personal_best(scores):
     scores.sort()
     return max(scores)

def personal_top_three(scores):
    scores.sort(reverse = True)
    if scores >= 3:
        return scores[:3]
    else: return scores
