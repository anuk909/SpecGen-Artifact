public class BellmanFord {

  static final int INFINITY = Integer.MAX_VALUE;

  static int[] runBellmanFord(int N, int D[], int src) {
    // Initialize distances.
    int dist[] = new int[N];
    boolean infinite[] = new boolean[N];
    for (int i = 0; i < N; i++) { // V+1 branches
      dist[i] = INFINITY;
      infinite[i] = true;
    }
    dist[src] = 0;
    infinite[src] = false;

    // Keep relaxing edges until either:
    //  (1) No more edges need to be updated.
    //  (2) We have passed through the edges N times.
    int k;
    for (k = 0; k < N; k++) { // V+1 branches
      boolean relaxed = false;
      for (int i = 0; i < N; i++) { // V(V+1) branches
        for (int j = 0; j < N; j++) { // V^2(V+1) branches
          if (i == j) continue; // V^3 branches
          if (!infinite[i]) { // V^2(V-1) branches
            if (dist[j] > dist[i] + D[i * N + j]) { // V^2(V-1) branches
              dist[j] = dist[i] + D[i * N + j];
              infinite[j] = false;
              relaxed = true;
            }
          }
        }
      }
      if (!relaxed) // V branches
      break;
    }

    // Check for negative-weight egdes.
    if (k == N) { // 1 branch
      // We relaxed during the N-th iteration, so there must be
      // a negative-weight cycle.
    }

    // Return the computed distances.
    return dist;
  }
}
