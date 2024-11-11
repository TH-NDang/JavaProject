// src/components/public/ProjectList.tsx
import React, { useEffect, useState } from "react";
import { ProjectService } from "../../services/project.service"; // Adjusted for correct path
import { ProjectDTO } from "../../types/project"; // Adjusted for correct path

const ProjectList: React.FC = () => {
  const [projects, setProjects] = useState<ProjectDTO[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const projectsData = await ProjectService.getAllProjects();
        setProjects(projectsData);
      } catch (err) {
        setError("Failed to load projects");
      } finally {
        setLoading(false);
      }
    };

    fetchProjects();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h2>Projects</h2>
      <ul>
        {projects.map((project) => (
          <li key={project.id}>
            <h3>{project.name}</h3>
            <p>{project.description}</p>
            <p>Start Date: {project.startDate}</p>
            <p>End Date: {project.endDate || "N/A"}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ProjectList;
