class Task {
    String name;
    String difficulty;
    int deadline;
    String progress;

    public Task(String name, String difficulty, int deadline) {
        this.name = name;
        this.difficulty = difficulty;
        this.deadline = deadline;
        this.progress = "Incomplete";
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String toString() {
        return "Task: " + name + ", Difficulty: " + difficulty + ", Deadline: " + deadline + ", Progress: " + progress;
    }
}