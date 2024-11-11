// src/pages/public/ProjectPage.tsx
import React from "react";
import ProjectList from "../../components/modals/ProjectList";

const ProjectPage: React.FC = () => {
    return (
        <div>
            <h1>Our Projects</h1>
            <ProjectList />
            {/* Add other project-related content here */}
        </div>
    );
};

export default ProjectPage;
