// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.apache.cloudstack.storage.volume.db;

import java.util.List;

import org.apache.cloudstack.storage.volume.Volume;
import org.apache.cloudstack.storage.volume.VolumeEvent;
import org.apache.cloudstack.storage.volume.VolumeState;
import org.apache.cloudstack.storage.volume.disktype.VolumeDiskType;
import org.apache.cloudstack.storage.volume.type.VolumeType;

import com.cloud.hypervisor.Hypervisor.HypervisorType;
import com.cloud.storage.Storage.ImageFormat;
import com.cloud.utils.Pair;
import com.cloud.utils.db.GenericDao;
import com.cloud.utils.fsm.StateDao;

public interface VolumeDao extends GenericDao<VolumeVO, Long>, StateDao<VolumeState, VolumeEvent, VolumeVO> {
    
	List<VolumeVO> findDetachedByAccount(long accountId);
	
    List<VolumeVO> findByAccount(long accountId);
    
    Pair<Long, Long> getCountAndTotalByPool(long poolId);
    
    Pair<Long, Long> getNonDestroyedCountAndTotalByPool(long poolId);
    
    List<VolumeVO> findByInstance(long id);
    
    List<VolumeVO> findByInstanceAndType(long id, VolumeType vType);
    
    List<VolumeVO> findByInstanceIdDestroyed(long vmId);
    
    List<VolumeVO> findByAccountAndPod(long accountId, long podId);
    
    List<VolumeVO> findByTemplateAndZone(long templateId, long zoneId);
    
    void deleteVolumesByInstance(long instanceId);
    
    void attachVolume(long volumeId, long vmId, long deviceId);
    
    void detachVolume(long volumeId);
    
    boolean isAnyVolumeActivelyUsingTemplateOnPool(long templateId, long poolId);
    
    List<VolumeVO> findCreatedByInstance(long id);
    
    List<VolumeVO> findByPoolId(long poolId);
    
	List<VolumeVO> findByInstanceAndDeviceId(long instanceId, long deviceId);
	
    List<VolumeVO> findUsableVolumesForInstance(long instanceId);
    
    Long countAllocatedVolumesForAccount(long accountId); 
   
    HypervisorType getHypervisorType(long volumeId);
    
    List<VolumeVO> listVolumesToBeDestroyed();
    
    ImageFormat getImageFormat(Long volumeId);
    
    List<VolumeVO> findReadyRootVolumesByInstance(long instanceId);
    
    List<Long> listPoolIdsByVolumeCount(long dcId, Long podId, Long clusterId, long accountId);

    VolumeVO allocVolume(long size, VolumeType type, String volName, Long templateId);
}
