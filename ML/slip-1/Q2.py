import pandas as pd
import matplotlib.pyplot as plt

# 1. Load CSV
df = pd.read_csv("./CSV/Iris.csv")

print("Original Data:")
print(df.head())

# 2. Convert categorical species to numeric
df["species_code"] = df["Species"].astype("category").cat.codes

print("\nData after converting species to numeric:")
print(df.head())

# 3. Scatter plot
plt.scatter(df["SepalLengthCm"], df["SepalWidthCm"], c=df["species_code"])
plt.xlabel("Sepal Length (cm)")
plt.ylabel("Sepal Width (cm)")
plt.title("Scatter Plot: Sepal Length vs Sepal Width")
plt.grid(True)
plt.show()
