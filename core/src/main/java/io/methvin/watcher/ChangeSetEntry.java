package io.methvin.watcher;

import java.nio.file.Path;

import io.methvin.watcher.hashing.Hash;

public class ChangeSetEntry {
   private final Path    path;
   private final boolean isDirectory;
   private final Hash    hash;
   private final Path    rootPath;

   public ChangeSetEntry(Path path, boolean isDirectory, Hash hash, Path rootPath) {
      this.path = path;
      this.isDirectory = isDirectory;
      this.hash = hash;
      this.rootPath = rootPath;
   }

   public Path path() {
      return path;
   }

   public boolean directory() {
      return isDirectory;
   }

   public Hash hash() {
      return hash;
   }

   public Path rootPath() {
      return rootPath;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      ChangeSetEntry that = (ChangeSetEntry) o;

      // does not include the hashCode, as older entries need to be removed from sets.

      if (isDirectory != that.isDirectory) {
         return false;
      }
      if (!path.equals(that.path)) {
         return false;
      }

      return rootPath.equals(that.rootPath);
   }

   @Override
   public int hashCode() {
      int result = path.hashCode();

      // does not include the hashCode, as older entries need to be removed from sets.
      result = 31 * result + (isDirectory ? 1 : 0);
      result = 31 * result + rootPath.hashCode();
      return result;
   }
}