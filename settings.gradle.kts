rootProject.name = "Smaug"

pluginManagement {
	repositories {
		maven(url = "https://maven.quiltmc.org/repository/release") {
			name = "Quilt"
		}

		maven(url = "https://maven.fabricmc.net/") {
			name = "Fabric"
		}

		gradlePluginPortal()
		mavenCentral()
	}

	plugins {
		val kotlin: String by settings
		val loom: String by settings

		kotlin("jvm") version kotlin apply false
		id("org.quiltmc.loom") version loom apply false
	}
}
