package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;

import com.wg.banking.model.Branch;

public interface BranchDAO {
	public Branch getBranchById(String branchId) throws ClassNotFoundException, SQLException;

	public Branch getBranchByManagerId(String managerId) throws ClassNotFoundException, SQLException;

	public boolean addBranch(Branch branch) throws ClassNotFoundException, SQLException;

	public boolean deleteBranch(String branchId) throws ClassNotFoundException, SQLException;

	public List<Branch> getAllBranches() throws ClassNotFoundException, SQLException;

	public boolean updateBranch(Branch branch, String columnToUpdate) throws ClassNotFoundException, SQLException;
}
