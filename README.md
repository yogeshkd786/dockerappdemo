# dockerappdemo

## To run app locally
### Pre-requisite
1. Install skaffold
2. Install docker
3. Install kubectl cli
4. Install Kubernetes cluster locally
- Launch “Docker for Desktop” (tested with Mac/Windows). Go to Preferences:
        - choose “Enable Kubernetes”,
        - set CPUs to at least 3, and Memory to at least 6.0 GiB
        - on the "Disk" tab, set at least 32 GB disk space

5. Run `kubectl get nodes` to verify you're connected to “Kubernetes on Docker”
6. Execute command ** skaffold run ** or ** skaffold dev **